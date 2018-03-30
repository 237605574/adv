package com.adv.dao;

import com.adv.constants.FilePaths;
import com.adv.constants.ResultCodes;
import com.adv.pojo.ResultObj;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Administrator
 */


public class FileDao {
    private static Logger LOG = LogManager.getLogger(FileDao.class.getName());
    private LoadingCache<String, File> fileCache = CacheBuilder.newBuilder().build(
            new CacheLoader<String, File>() {
                @Override
                public File load(String fileName) {
                    return loadFile(fileName);
                }
            }
    );

    public static FileDao getInstance() {
        return SingletonHolder.instance;
    }

    private FileDao() {
    }

    private static class SingletonHolder {
        private static final FileDao instance = new FileDao();
    }

    /**
     * 根据路径获取file
     */
    public ResultObj<File> getFile(String fileName) {
        File file;
        try {
            file = fileCache.get(fileName);
        } catch (Exception e) {
            String msg;
            //  loadingCache如果返回值为null会报异常，但是这个异常我们是能够接受的
            if (CacheLoader.InvalidCacheLoadException.class == e.getClass()) {
                msg = "文件不存在：" + fileName;
            } else {
                msg = "getFile error:" + e.getMessage();
                LOG.log(Level.ERROR, msg);
            }
            return new ResultObj<>(ResultCodes.UNKNOWN_ERROR, msg);
        }
        return new ResultObj<>(ResultCodes.SUCCESS, file);
    }

    /**
     * 新增文件
     */
    public ResultObj<Void> saveFile(String fileName, MultipartFile file) {
        String savePath = getFilePath(fileName);
        File saveFile = new File(savePath);
        File parentFile = saveFile.getParentFile();
        if (saveFile.exists()) {
            saveFile.delete();
        } else {
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
        }
        fileCache.invalidate(fileName);
        try {
            file.transferTo(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
            if (saveFile.exists()) {
                saveFile.delete();
            }
            //  todo 记日志
            String msg = "服务器保存文件错误:" + fileName;
            LOG.log(Level.ERROR, msg);
            return new ResultObj<>(ResultCodes.UNKNOWN_ERROR, msg);
        }
        fileCache.put(fileName, saveFile);
        return new ResultObj<>(ResultCodes.SUCCESS);
    }

    public ResultObj<Void> deleteFile(String fileName) {
        if (fileName == null) {
            return new ResultObj<>(ResultCodes.PARAM_ERROR, "广告文件名为空");
        }
        String filePath = getFilePath(fileName);
        File file = new File(filePath);
        if (!file.exists()) {
            return new ResultObj<>(ResultCodes.UNKNOWN_ERROR, "广告文件不存在：" + fileName);
        } else {
            file.delete();
            fileCache.invalidate(fileName);
            return new ResultObj<>(ResultCodes.SUCCESS);
        }
    }

    private String getFilePath(String fileName) {
        return FilePaths.ADV_PATH + "/" + fileName;
    }

    private File loadFile(String fileName) {
        if (fileName == null) {
            return null;
        }
        String filePath = getFilePath(fileName);
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        return file;
    }
}
