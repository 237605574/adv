package com.adv.filter;

import com.adv.common.constants.LocalAddrs;
import com.adv.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lurongzhi
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AdministratorService administratorService;
    //不对匹配该值的访问路径拦截（正则）
    private final String NO_INTERCEPTOR_PATH = ".*/((login)|(download)|(logout)|(code)|(app)|(weixin)|(static)|(main)|(websocket)).*";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();
        if (path.matches(NO_INTERCEPTOR_PATH)) {
            //  不拦截
            return true;
        } else if (!administratorService.checkLogin(request.getSession())) {
            response.sendRedirect(request.getContextPath() + LocalAddrs.LOGIN);
            return false;
        }
        return true;
    }
}
