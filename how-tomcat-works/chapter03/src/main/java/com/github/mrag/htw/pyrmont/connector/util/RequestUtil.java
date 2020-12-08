package com.github.mrag.htw.pyrmont.connector.util;

import javax.servlet.http.HttpServletRequest;

public final class RequestUtil {

    /**
     * Build an appropriate return value for
     * {@link HttpServletRequest#getRequestURL()} based on the provided
     * request object. Note that this will also work for instances of
     * {@link javax.servlet.http.HttpServletRequestWrapper}.
     *
     * @param request The request object for which the URL should be built
     * @return The request URL for the given request object
     */
    public static StringBuilder getRequestURL(HttpServletRequest request) {
        StringBuilder url = new StringBuilder();
        String scheme = request.getScheme();
        int port = request.getServerPort();
        if (port < 0) {
            // Work around java.net.URL bug
            port = 80;
        }

        url.append(scheme);
        url.append("://");
        url.append(request.getServerName());
        boolean nonDefaultPort = ("http".equals(scheme) && (port != 80))
                || ("https".equals(scheme) && (port != 443));
        if (nonDefaultPort) {
            url.append(':');
            url.append(port);
        }
        url.append(request.getRequestURI());
        return url;
    }

    public static void parseParameters(ParameterMap results, String queryString, String encoding) {
        // TODO
    }
}