package com.github.mrag.helloim.interceptors;

import com.github.mrag.helloim.common.*;
import com.github.mrag.helloim.common.enums.UserStatus;
import com.github.mrag.helloim.service.ImUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class HttpTokenInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(HttpTokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean hasToken = false;
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
                UserStatus status = imUserService.getUserStatus(tokenInstance.getUserId(),
                                                                tokenInstance.getUsername());
                if (status != UserStatus.Normal) {
                    // token无效
                    throw Exceptions.tokenInvalidation();
                }
                hasToken = true;
                String checked = tokenUtils.isNeedUpdate(tokenInstance);
                if (checked != null) {
                    // token需要更新
                    response.setHeader(HttpToken.PERMISSION_HEADER_NAME, checked);
                }
                request.setAttribute("userId", tokenInstance.getUserId());
            }
        }
        log.debug("收到请求[{}], 是否携带token[{}]", request.getRequestURI(), hasToken);
        return true;
    }
}
