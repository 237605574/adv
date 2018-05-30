package com.adv.controller;

import com.adv.common.constants.ResultCodes;
import com.adv.entity.ResultObj;
import com.adv.entity.User;
import com.adv.service.AdvService;
import com.adv.common.utils.GsonUtils;
import com.adv.common.utils.TimeCostUtils;
import com.fasterxml.jackson.databind.util.JSONPObject;
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
    @RequestMapping(value = "/getAdvListByUser", method = RequestMethod.POST, produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    public String getAdvList(HttpServletRequest request, HttpServletResponse response, User user, HttpSession session) {
        return GsonUtils.toJson(advService.getAdvList(user));
    }

    /**
     * 获取全部有效广告
     */
    @RequestMapping(value = "/getAllAdvList", method = RequestMethod.GET)
    @ResponseBody
    public Object getAllAdvList(String callback) {
        return new JSONPObject(callback, advService.getAllAdvList());

    }


    /**
     * 根据文件名下载广告
     */
    @RequestMapping(value = "/getAdvFile", method = RequestMethod.POST, produces = {"application/json; charset=utf-8"})
    public String getAdvFile(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "fileName") String fileName) {
//        header('Access-Control-Allow-Origin:http://localhost:8008');//设置允许访问的域
//        header('Access-Control-Allow-Credentials: true');//设置是否发送cookie，需要在客户端同时设置才会生效
//        header('Access-Control-Allow-Headers:X-Requested-With');//允许的访问头部，也需要在客户端同时设置，同时为了避免跨域访问出错，客户端最好不要设置该项信息，避免服务端不允许
//        header('Access-Control-Allow-Methods: GET, PUT, POST, DELETE');//支持的请求方式
//        response.addHeader("Access-Control-Allow-Origin");
        TimeCostUtils.getInstance().start();
        ResultObj<File> fileResultObj = advService.getFile(fileName);
        TimeCostUtils.getInstance().setPoint("从dao中获取文件");
        if (fileResultObj.isSuccess()) {
            System.out.println("file success");
            InputStream inputStream = null;
            BufferedOutputStream bufferedOutputStream = null;
            try {
                inputStream = new BufferedInputStream(new FileInputStream(fileResultObj.getData()));
                TimeCostUtils.getInstance().setPoint("创建inputstream");
                response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
                //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
                response.setContentType("multipart/form-data");
                bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
                int len = 0;
                byte buf[] = new byte[102400];
                TimeCostUtils.getInstance().setPoint("创建bufferoutputsream");
                while ((len = inputStream.read(buf)) != -1) {
                    bufferedOutputStream.write(buf, 0, len);
                    bufferedOutputStream.flush();
                }
                TimeCostUtils.getInstance().setPoint("写进OutputStream");
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
            TimeCostUtils.getInstance().setPoint("关闭文件");
            TimeCostUtils.getInstance().print();
            return GsonUtils.toJson(new ResultObj<>(ResultCodes.SUCCESS));
        }
        TimeCostUtils.getInstance().setPoint("关闭文件");
        TimeCostUtils.getInstance().print();
        return GsonUtils.toJson(fileResultObj);
    }
}
