package com.github.mrag.mvc.common;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Gmw
 */
public class CommonFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("过滤器 => before");
        chain.doFilter(request, response);
        System.out.println("过滤器 => after");
    }
}
