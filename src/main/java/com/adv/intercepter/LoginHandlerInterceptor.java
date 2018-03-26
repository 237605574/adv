package com.adv.intercepter;

import com.adv.constants.LocalAddrs;
import com.adv.constants.SessionAttribute;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lurongzhi
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {
    private final String NO_INTERCEPTOR_PATH = ".*/((login)|(reg)|(logout)|(code)|(app)|(weixin)|(static)|(main)|(websocket)).*";    //不对匹配该值的访问路径拦截（正则）

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        if (path.matches(NO_INTERCEPTOR_PATH)) {
            //  不拦截
            return true;
        } else if (request.getSession().getAttribute(SessionAttribute.LOGIN_INFO) == null) {
            response.sendRedirect(request.getContextPath() + LocalAddrs.LOGIN);
            return false;
        }
        return true;
    }
}
