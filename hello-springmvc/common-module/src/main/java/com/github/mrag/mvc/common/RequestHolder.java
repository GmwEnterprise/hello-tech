package com.github.mrag.mvc.common;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Gmw
 */
public final class RequestHolder {

    private static final ThreadLocal<HttpServletRequest> REQUEST_HOLDER = new ThreadLocal<>();

    public static HttpServletRequest get() {
        return REQUEST_HOLDER.get();
    }

    public static void set(HttpServletRequest request) {
        REQUEST_HOLDER.set(request);
    }

    public static void remove() {
        REQUEST_HOLDER.remove();
    }
}
