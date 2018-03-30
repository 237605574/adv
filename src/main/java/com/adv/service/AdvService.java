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
    private AdvTagDao advTagDao;

    private FileDao fileDao = FileDao.getInstance();

    public ResultObj<Void> checkAdvInfo(AdvObj advObj) {
        if (advObj == null) {
            return new ResultObj<>(ResultCodes.ADV_INFO_NOT_FOUND);
        } else if (advObj.getName() == null || advObj.getName().equals("")) {
            //  广告名称为空
            return new ResultObj<>(ResultCodes.EMPTY_NAME_ERROR);
        } else if (!isValidName(advObj)) {
            //  广告名字重复
            return new ResultObj<>(ResultCodes.ADV_NAME_DUPLICATE, String.format("已经有该广告名字了:%s", advObj.getName()));
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
        String fileNamePattern = "(.*((gif)|(jpg)|(jpeg)|(txt)))";
        if (advObj == null) {
            //  广告信息为空
            return new ResultObj<>(ResultCodes.ADV_INFO_NOT_FOUND);
        } else if (file == null || file.isEmpty()) {
            // 文件为空
            return new ResultObj<>(ResultCodes.EMPTY_FILE);
        } else if (!file.getOriginalFilename().matches(fileNamePattern)) {
            //  文件名字不符合
            return new ResultObj<>(ResultCodes.FILE_NAME_ERROR, String.format("名称后缀不符合：%s", file.getOriginalFilename()));
        }
        return new ResultObj<>(ResultCodes.SUCCESS);
    }

    public ResultObj<Void> addAdv(AdvObj advObj, MultipartFile uploadFile) {
        //  双重检查。因为要涉及到数据库操作，所以需要同步加锁，每次只能添加一个广告。
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
            int userDaoResult = userAdvDao.addAdvByTag(advObj);
            return new ResultObj<>(ResultCodes.SUCCESS);
        }
    }

    public ResultObj<List<AdvObj>> getAdvList(User user) {
        if (user == null || user.getId() == null) {
            return new ResultObj<>(ResultCodes.USER_ID_ERROR, "用户ID找不到");
        }
        List<AdvObj> advObjList = advDao.getAdvListByUser(user);
        return new ResultObj<>(ResultCodes.SUCCESS, advObjList);
    }

    private ResultObj<Void> checkUserTag(AdvObj advObj) {
        int result = userTagDao.checkTagByAdv(advObj);
        if (result <= 0) {
            return new ResultObj<>(ResultCodes.USER_TAG_NOT_FOUND, "用户标签xxx不存在");
        } else {
            return new ResultObj<>(ResultCodes.SUCCESS);
        }

    }

    public ResultObj<File> getFile(String fileName) {
        return fileDao.getFile(fileName);
    }

    public ResultObj<Void> updateAdv(AdvObj advObj) {
        ResultObj<Void> infoResult = checkAdvInfo(advObj);
        if (!infoResult.isSuccess()) {
            return infoResult;
        }
        int result = advDao.updateAdv(advObj);
        if (result <= 0) {
            return new ResultObj<>(ResultCodes.DATABASE_ERROR, "更新数据库错误");
        }
        return new ResultObj<>(ResultCodes.SUCCESS);
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
