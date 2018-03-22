package com.adv.service;

import com.adv.constants.FilePaths;
import com.adv.constants.ResultCodes;
import com.adv.dao.AdvDao;
import com.adv.dao.UserAdvDao;
import com.adv.dao.UserTagDao;
import com.adv.idgenerator.IdMgr;
import com.adv.pojo.AdvObj;
import com.adv.pojo.ResultObj;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author lurongzhi
 */
@Service
public class AdvService {
    @Autowired
    private AdvDao advDao;
    @Autowired
    private UserAdvDao userAdvDao;
    @Autowired
    private UserTagDao userTagDao;


    public ResultObj<Void> checkAdvInfo(AdvObj advObj) {
        if (advObj == null) {
            return new ResultObj<>(ResultCodes.ADV_INFO_NOT_FOUND);
        } else if (advObj.getName() == null || advObj.getName().equals("")) {
            //  广告名称为空
            return new ResultObj<>(ResultCodes.EMPTY_NAME_ERROR);
        } else if (!checkName(advObj)) {
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
            if (advObj.getValidDate().getTime() < new Date().getTime()) {
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
            String savePath = FilePaths.ADV_PATH + "/" + saveFileName;
            File saveFile = new File(savePath);
            File parentFile = saveFile.getParentFile();
            if (saveFile.exists()) {
                saveFile.delete();
            } else {
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
            }
            try {
                FileUtils.copyInputStreamToFile(uploadFile.getInputStream(), saveFile);
            } catch (IOException e) {
                e.printStackTrace();
                //  todo 记日志
                return new ResultObj<>(ResultCodes.UNKNOWN_ERROR, "服务器保存文件错误");
            }
            //  插入到数据库
            advObj.setFileUrl(saveFileName);
            int advDaoResult = advDao.addAdv(advObj);
            if (advDaoResult <= 0) {
                return new ResultObj<>(ResultCodes.UNKNOWN_ERROR, "保存广告信息到数据库错误");
            }
            //  更新目标用户的广告列表
            int userDaoResult = userAdvDao.addAdvByTag(advObj);
            return new ResultObj<>(ResultCodes.SUCCESS);
        }
    }


    private ResultObj<Void> checkUserTag(AdvObj advObj) {
        int result = userTagDao.checkTagByAdv(advObj);
        if (result <= 0) {
            return new ResultObj<>(ResultCodes.USER_TAG_NOT_FOUND, "用户标签xxx不存在");
        }else {
            return new ResultObj<>(ResultCodes.SUCCESS);
        }

    }

    private boolean checkName(AdvObj advObj) {
        return advDao.checkNameCount(advObj) == 0;
    }
}
