package com.adv.controller;

import com.adv.pojo.ResultObj;
import com.adv.pojo.User;
import com.adv.service.AdvService;
import com.adv.utils.GsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;


/**
 * @author lurongzhi
 */
@Controller
@RequestMapping("/download")
public class DownloadController {
    private static Logger LOG = LogManager.getLogger(DownloadController.class);
    @Autowired
    private AdvService advService;

    /**
     * 用户获取广告流程：用户获取广告列表 -> 客户端根据获取到的广告信息再决定要拉取哪些广告
     */
    @RequestMapping(value = "/getAdvList", method = RequestMethod.POST, produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    public String getAdvList(HttpServletRequest request, HttpServletResponse response, User user, HttpSession session) {
        return GsonUtils.toJson(advService.getAdvList(user));
    }

    /**
     * 根据文件名下载广告
     */
    @RequestMapping(value = "/getAdvFile", method = RequestMethod.POST,produces = {"application/json; charset=utf-8"})
    public String getAdvFile(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "fileName") String fileName) {
        ResultObj<File> fileResultObj = advService.getFile(fileName);
        if (fileResultObj.isSuccess()) {
            System.out.println("file success");
            InputStream inputStream = null;
            BufferedOutputStream bufferedOutputStream = null;
            try {
                inputStream = new BufferedInputStream(new FileInputStream(fileResultObj.getData()));
                response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
                //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
                response.setContentType("multipart/form-data");
                bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
                int len = 0;
                while ((len = inputStream.read()) != -1) {
                    bufferedOutputStream.write(len);
                    bufferedOutputStream.flush();
                }
            } catch (IOException e) {
                LOG.error("读取文件错误", e);
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                } catch (Exception e) {
                    LOG.error("关闭文件错误", e);
                }
            }
        }
        return GsonUtils.toJson(fileResultObj);
    }
}
