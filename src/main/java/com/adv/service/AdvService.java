package com.adv.service;

import com.adv.constants.ResultCodes;
import com.adv.dao.*;
import com.adv.idgenerator.IdMgr;
import com.adv.pojo.AdvObj;
import com.adv.pojo.ResultObj;
import com.adv.pojo.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author lurongzhi
 */
@Service
public class AdvService {
    private static Logger LOG = LogManager.getLogger(AdvService.class.getName());

    @Autowired
    private AdvDao advDao;
    @Autowired
    private UserAdvDao userAdvDao;
    @Autowired
    private UserTagDao userTagDao;

    @Autowired
    private TagDao tagDao;

    @Autowired
    private AdvTagDao advTagDao;

    private FileDao fileDao = FileDao.getInstance();


    /**
     * 和添加广告时的判断函数相比，多了个判断id是否有效
     */
    public ResultObj<Void> checkUpdateAdvInfo(AdvObj advObj) {
        ResultObj<Void> errorMsg = new ResultObj<>(ResultCodes.ADV_INFO_ERROR, "广告id错误");
        Long id = advObj.getId();
        if (id == null) {
            return errorMsg;
        }
        int dbResult = advDao.checkIdCount(id);
        if (dbResult == 0) {
            return errorMsg;
        }
        return new ResultObj<>(ResultCodes.SUCCESS);
    }

    public ResultObj<Void> checkAdvInfo(AdvObj advObj) {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date minDate = null;
        Date maxDate = null;
        try {
            minDate = dateFormat1.parse("1900-01-01");
            maxDate = dateFormat1.parse("2200-01-01");
        } catch (ParseException e) {
            return new ResultObj<>(ResultCodes.UNKNOWN_ERROR, "服务器错误");
        }
        if (advObj == null) {
            return new ResultObj<>(ResultCodes.ADV_INFO_NOT_FOUND, "找不到广告信息");
        } else if (advObj.getName() == null || advObj.getName().equals("")) {
            //  广告名称为空
            return new ResultObj<>(ResultCodes.EMPTY_NAME_ERROR, "广告名字为空");
        } else if (!isValidName(advObj)) {
            //  广告名字重复
            return new ResultObj<>(ResultCodes.ADV_NAME_DUPLICATE, String.format("已经有该广告名字了:%s", advObj.getName()));
        } else if (advObj.getStartDate() == null) {
            return new ResultObj<>(ResultCodes.DATE_ERROR, "开始日期为空");
        } else if (advObj.getEndDate() == null) {
            return new ResultObj<>(ResultCodes.DATE_ERROR, "结束日期为空");
        } else if (advObj.getDisplayDetail() == null) {
            return new ResultObj<>(ResultCodes.ADV_INFO_ERROR, "投放时间段错误");
        } else if (advObj.getHomepage() == null) {
            return new ResultObj<>(ResultCodes.ADV_INFO_ERROR, "广告主页错误");
        } else if (advObj.getStartDate().before(minDate) || advObj.getStartDate().after(maxDate)) {
            return new ResultObj<>(ResultCodes.DATE_ERROR, "开始日期超出限制");
        } else if (advObj.getEndDate().before(minDate) || advObj.getEndDate().after(maxDate)) {
            return new ResultObj<>(ResultCodes.DATE_ERROR, "截止日期超出限制");
        } else {
            //  检查广告目标用户标签
            ResultObj<Void> userTagResult = checkUserTag(advObj);
            if (userTagResult.getCode() != ResultCodes.SUCCESS) {
                return userTagResult;
            }
        }
        //  广告信息没有错误
        return new ResultObj<>(ResultCodes.SUCCESS);
    }

    public ResultObj<Void> checkAdvFile(AdvObj advObj, MultipartFile file) {
        //  文件后缀限定
        //  TODO :根据广告类型来进行后缀限定
        String fileNamePattern = "(.*((gif)|(jpg)|(jpeg)|(png)))";
        if (advObj == null) {
            //  广告信息为空
            return new ResultObj<>(ResultCodes.ADV_INFO_NOT_FOUND, "广告信息为空");
        } else if (file == null || file.isEmpty()) {
            // 文件为空
            System.out.println(file);
            return new ResultObj<>(ResultCodes.EMPTY_FILE, "文件为空");
        } else if (!file.getOriginalFilename().matches(fileNamePattern)) {
            //  文件名字不符合
            return new ResultObj<>(ResultCodes.FILE_NAME_ERROR, String.format("名称后缀不符合：%s", file.getOriginalFilename()));
        }
        return new ResultObj<>(ResultCodes.SUCCESS);
    }

    public ResultObj<Void> addAdv(AdvObj advObj, MultipartFile uploadFile) {
        //  双重检查。因为要涉及到数据库操作，所以需要同步加锁，每次只能添加一个广告。
        //  主要是如果同时添加了两个广告，可能会出现名字重复的问题
        synchronized (AdvService.class) {
            //  检查广告信息
            ResultObj<Void> infoResult = checkAdvInfo(advObj);
            if (!infoResult.isSuccess()) {
                return infoResult;
            }
            //  检查广告是否有效
            if (advObj.getEndDate().getTime() < new Date().getTime()) {
                advObj.setValid(false);
            }
            //  检查文件合法性
            ResultObj<Void> fileResult = checkAdvFile(advObj, uploadFile);
            if (!fileResult.isSuccess()) {
                return fileResult;
            }
            //  保存文件到本地，文件名字使用广告Id+后缀
            Long advId = IdMgr.getInstance().genAdvId();
            advObj.setId(advId);
            String uploadFileName = uploadFile.getOriginalFilename();
            String saveFileName = advId + uploadFileName.substring(uploadFileName.indexOf("."));
            ResultObj<Void> saveResult = fileDao.saveFile(saveFileName, uploadFile);
            if (!saveResult.isSuccess()) {
                return saveResult;
            }
            //  插入到数据库
            advObj.setFileUrl(saveFileName);
            int advDaoResult = advDao.addAdv(advObj);
            if (advDaoResult <= 0) {
                return new ResultObj<>(ResultCodes.DATABASE_ERROR, "保存广告信息到数据库错误");
            } else {
                //  保存广告标签
                if (advObj.getUserTagIds() != null && advObj.getUserTagIds().size() != 0) {
                    int advTagDaoResult = advTagDao.addTag(advObj);
                    if (advTagDaoResult <= 0) {
                        return new ResultObj<>(ResultCodes.DATABASE_ERROR, "保存广告标签信息到数据库错误");
                    }
                }
            }
            //  更新目标用户的广告列表
            updateUserAdv(advObj);
            return new ResultObj<>(ResultCodes.SUCCESS);
        }
    }

    public ResultObj<List<AdvObj>> queryAdv(AdvObj advObj, int offset, int limit) {
        if (offset < 0 || limit <= 0) {
            return new ResultObj<>(ResultCodes.PARAM_ERROR, "offset与limit不能为0");
        }
        List<AdvObj> advObjs = advDao.queryAdv(advObj, offset, limit);
        if (advObjs == null) {
            advObjs = new ArrayList<>();
        } else {
            for (AdvObj queryAdvObj : advObjs) {
                Long advId = queryAdvObj.getId();
                if (advId != null) {
                    List<Long> tagIds = advTagDao.getTag(advId);
                    queryAdvObj.setUserTagIds(tagIds);
                }
            }
        }
        return new ResultObj<>(ResultCodes.SUCCESS, advObjs);
    }

    public ResultObj<List<AdvObj>> getAdvList(User user) {
        if (user == null || user.getId() == null) {
            return new ResultObj<>(ResultCodes.USER_ID_ERROR, "用户ID找不到");
        }
        List<AdvObj> advObjList = advDao.getAdvListByUser(user);
        return new ResultObj<>(ResultCodes.SUCCESS, advObjList);
    }

    private ResultObj<Void> checkUserTag(AdvObj advObj) {
        int result = tagDao.checkTagByAdv(advObj);
        if (result <= 0) {
            return new ResultObj<>(ResultCodes.USER_TAG_NOT_FOUND, "用户标签不存在,请重新选择目标用户标签");
        } else {
            return new ResultObj<>(ResultCodes.SUCCESS);
        }

    }

    public ResultObj<File> getFile(String fileName) {
        return fileDao.getFile(fileName);
    }

    /**
     * 添加流程为：检查广告信息 -> 判断是否更新文件 ->
     * 更新文件-> 更新数据库
     */
    public ResultObj<Void> updateAdv(AdvObj advObj, MultipartFile advFile) {
        synchronized (AdvService.class) {
            //  检查广告信息
            ResultObj<Void> infoResult = checkUpdateAdvInfo(advObj);
            if (!infoResult.isSuccess()) {
                return infoResult;
            }
            //  检查广告是否有效
            if (advObj.getEndDate().getTime() < new Date().getTime()) {
                advObj.setValid(false);
            }
            Long advId = advObj.getId();
            //  获取数据库中原广告信息
            AdvObj dbAdv = advDao.getAdv(advId);
            if (dbAdv == null) {
                return new ResultObj<>(ResultCodes.PARAM_ERROR, "广告ID有误");
            }
            if (advFile != null) {
                //  检查文件合法性
                ResultObj<Void> fileResult = checkAdvFile(advObj, advFile);
                if (!fileResult.isSuccess()) {
                    return fileResult;
                }
                // 保存到本地
                String uploadFileName = advFile.getOriginalFilename();
                String saveFileName = advId + uploadFileName.substring(uploadFileName.indexOf("."));
                ResultObj<Void> saveResult = fileDao.saveFile(saveFileName, advFile);
                if (!saveResult.isSuccess()) {
                    return saveResult;
                }
                advObj.setFileUrl(saveFileName);
            }

            //  插入到数据库
            int advDaoResult = advDao.updateAdv(advObj);
            if (advDaoResult <= 0) {
                return new ResultObj<>(ResultCodes.DATABASE_ERROR, "更新广告信息到数据库错误");
            } else {
                //  先删除原本的标签，再添加新的标签
                if (advObj.getUserTagIds() != null && advObj.getUserTagIds().size() != 0) {
                    int delResult = advDao.deleteAdv(advId);
                    int advTagDaoResult = advTagDao.addTag(advObj);
                    if (advTagDaoResult <= 0) {
                        return new ResultObj<>(ResultCodes.DATABASE_ERROR, "保存广告标签信息到数据库错误");
                    }
                }
            }
            //  更新目标用户的广告列表
            updateUserAdv(advObj);
            int result = advDao.updateAdv(advObj);
            if (result <= 0) {
                return new ResultObj<>(ResultCodes.DATABASE_ERROR, "更新数据库错误");
            }
            if (advFile != null && !dbAdv.getFileUrl().equals(advObj.getFileUrl())) {
                //  删除原本的广告文件
                fileDao.deleteFile(dbAdv.getFileUrl());
            }
            return new ResultObj<>(ResultCodes.SUCCESS);
        }
    }

    private void updateUserAdv(AdvObj advObj) {
        int userDaoResult = userAdvDao.addAdvByTag(advObj);
    }

    /**
     * 从数据库删除特定广告，流程为：
     * 更新用户广告映射表 -> 更新广告表 -> 删除保存的文件
     */
    public ResultObj<Void> removeAdv(Long advId) {
        if (advId == null) {
            return new ResultObj<>(ResultCodes.PARAM_ERROR, "广告ID为空");
        }
        AdvObj advObj = advDao.getAdv(advId);
        if (advObj == null) {
            return new ResultObj<>(ResultCodes.PARAM_ERROR, "广告ID不存在");
        }
        //  删除用户广告映射表中的广告
        int userAdvResult = userAdvDao.removeAdv(advObj);
        //  删除广告表格
        int advResult = advDao.deleteAdv(advId);
        //  删除失败
        if (advResult <= 0) {
            String msg = "广告文件删除失败";
            LOG.log(Level.ERROR, msg);
            return new ResultObj<>(ResultCodes.DATABASE_ERROR, msg);
        } else {
            // 获取广告文件名字
            String fileName = advObj.getFileUrl();
            return fileDao.deleteFile(fileName);
        }
    }

    public ResultObj<Void> checkUpdateId(Long advId) {
        if(advId==null){
            return new ResultObj<>(ResultCodes.PARAM_ERROR,"id为空");
        }
        int count = advDao.checkIdCount(advId);
        if (count <= 0) {
            return new ResultObj<>(ResultCodes.DATABASE_ERROR, "id不存在");
        }
        return new ResultObj<>(ResultCodes.SUCCESS);
    }

    public ResultObj<AdvObj> queryAdvById(Long advId){
        if (advId == null) {
            return new ResultObj<>(ResultCodes.PARAM_ERROR, "广告ID为空");
        }
        AdvObj advObj = advDao.getAdv(advId);
        if(advObj==null){
            return new ResultObj<>(ResultCodes.ADV_INFO_ERROR,"找不到广告");
        }
        List<Long> tagList = advTagDao.getTag(advId);
        advObj.setUserTagIds(tagList);
        return new ResultObj<>(ResultCodes.SUCCESS,advObj);
    }


    /**
     * 用于定时更新数据库中的过期广告
     */
    public int updateAdvState() {
        return advDao.updateState();
    }


    /**
     * @return 名字没有重复: true
     * 名字重复:   false
     */
    private boolean isValidName(AdvObj advObj) {
        return advDao.checkNameCount(advObj) == 0;
    }
}
