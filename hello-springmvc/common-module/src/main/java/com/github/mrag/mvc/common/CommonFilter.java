package com.github.mrag.mvc.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Gmw
 */
public class CommonFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(CommonFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("过滤器 => before");
        displayRequestMetadata(request);
        chain.doFilter(request, response);
        System.out.println("过滤器 => after");
    }

    private void displayRequestMetadata(ServletRequest request) {
        log.info("remoteHost={}, remotePort={}", request.getRemoteHost(), request.getRemotePort());
    }
}
