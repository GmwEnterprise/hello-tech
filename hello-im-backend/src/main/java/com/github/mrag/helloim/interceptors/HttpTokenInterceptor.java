package com.github.mrag.helloim.interceptors;

import com.github.mrag.helloim.common.*;
import com.github.mrag.helloim.service.ImUserService;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class HttpTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            Permission permission = hm.getMethodAnnotation(Permission.class);
            if (permission != null) {
                String token = request.getHeader(HttpToken.PERMISSION_HEADER_NAME);
                if (StringUtils.isEmpty(token)) {
                    // token缺失
                    throw Exceptions.tokenMissing();
                }
                HttpTokenUtils tokenUtils = ComponentUtils.getBean(HttpTokenUtils.class);
                HttpToken tokenInstance = tokenUtils.tokenDeserialize(token);
                if (tokenInstance.getExpState()) {
                    // token过期
                    throw Exceptions.tokenExpiration();
                }
                ImUserService imUserService = ComponentUtils.getBean(ImUserService.class);
                Enums.UserStatus status = imUserService.getUserStatus(tokenInstance.getUserId(),
                                                                      tokenInstance.getUsername());
                if (status != Enums.UserStatus.NORMAL) {
                    // token无效
                    throw Exceptions.tokenInvalidation();
                }
                String checked = tokenUtils.isNeedUpdate(tokenInstance);
                if (checked != null) {
                    // token需要更新
                    response.setHeader(HttpToken.PERMISSION_HEADER_NAME, checked);
                }
                request.setAttribute("userId", tokenInstance.getUserId());
            }
        }
        return true;
    }
}
