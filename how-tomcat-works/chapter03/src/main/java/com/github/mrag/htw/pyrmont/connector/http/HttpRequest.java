package com.github.mrag.htw.pyrmont.connector.http;

import com.github.mrag.htw.pyrmont.connector.RequestStream;
import com.github.mrag.htw.pyrmont.connector.util.Enumerator;
import com.github.mrag.htw.pyrmont.connector.util.ParameterMap;
import com.github.mrag.htw.pyrmont.connector.util.RequestUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HttpRequest implements HttpServletRequest {

    // 请求体属性

    private String contentType;
    private int contentLength;
    private InetAddress inetAddress;
    private InputStream input;
    private String method;
    private String protocol;
    private String queryString;
    private String requestURI;
    private String serverName;
    private int serverPort;
    private Socket socket;
    private boolean requestedSessionCookie;
    private String requestedSessionId;
    private boolean requestedSessionURL;

    /**
     * 请求属性键值
     */
    protected Map<String, Object> attributes = new HashMap<>();
    /**
     * 授权凭据
     */
    protected String authorization = null;
    /**
     * 请求上下文路径
     */
    protected String contextPath = "";
    /**
     * 与此请求关联的Cookie集合
     */
    protected final List<Cookie> cookies = new ArrayList<>();
    /**
     * An empty collection to use for returning empty Enumerations.  Do not
     * add any elements to this collection!
     */
    protected static List<String> empty = new ArrayList<>();
    /**
     * The set of SimpleDateFormat formats to use in getDateHeader().
     */
    protected SimpleDateFormat[] formats = {
            new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US),
            new SimpleDateFormat("EEEEEE, dd-MMM-yy HH:mm:ss zzz", Locale.US),
            new SimpleDateFormat("EEE MMMM d HH:mm:ss yyyy", Locale.US)
    };

    /**
     * The HTTP headers associated with this Request, keyed by name.  The
     * values are ArrayLists of the corresponding header values.
     */
    protected final Map<String, List<String>> headers = new HashMap<>();
    /**
     * The parsed parameters for this request.  This is populated only if
     * parameter information is requested via one of the
     * <code>getParameter()</code> family of method calls.  The key is the
     * parameter name, while the value is a String array of values for this
     * parameter.
     * <p>
     * <strong>IMPLEMENTATION NOTE</strong> - Once the parameters for a
     * particular request are parsed and stored here, they are not modified.
     * Therefore, application level access to the parameters need not be
     * synchronized.
     */
    protected ParameterMap<String, String[]> parameters = null;

    /**
     * Have the parameters for this request been parsed yet?
     */
    protected boolean parsed = false;
    protected String pathInfo = null;

    /**
     * The reader that has been returned by <code>getReader</code>, if any.
     */
    protected BufferedReader reader = null;

    /**
     * The ServletInputStream that has been returned by
     * <code>getInputStream()</code>, if any.
     */
    protected ServletInputStream stream = null;

    public HttpRequest(InputStream input) {
        this.input = input;
    }

    public void addHeader(String name, String value) {
        name = name.toLowerCase();
        synchronized (headers) {
            List<String> values = headers.computeIfAbsent(name, k -> new ArrayList<>());
            values.add(value);
        }
    }

    /**
     * Parse the parameters of this request, if it has not already occurred.
     * If parameters are present in both the query string and the request
     * content, they are merged.
     */
    protected void parseParameters() {
        if (parsed)
            return;
        ParameterMap<String, String[]> results = parameters;
        if (results == null)
            results = new ParameterMap<>();
        results.setLocked(false);
        String encoding = getCharacterEncoding();
        if (encoding == null)
            encoding = "ISO-8859-1";

        // Parse any parameters specified in the query string
        String queryString = getQueryString();
        try {
            RequestUtil.parseParameters(results, queryString, encoding);
        } catch (UnsupportedEncodingException e) {
            ;
        }

        // Parse any parameters specified in the input stream
        String contentType = getContentType();
        if (contentType == null)
            contentType = "";
        int semicolon = contentType.indexOf(';');
        if (semicolon >= 0) {
            contentType = contentType.substring(0, semicolon).trim();
        } else {
            contentType = contentType.trim();
        }
        if ("POST".equals(getMethod()) && (getContentLength() > 0)
                && "application/x-www-form-urlencoded".equals(contentType)) {
            try {
                int max = getContentLength();
                int len = 0;
                byte[] buf = new byte[getContentLength()];
                ServletInputStream is = getInputStream();
                while (len < max) {
                    int next = is.read(buf, len, max - len);
                    if (next < 0) {
                        break;
                    }
                    len += next;
                }
                is.close();
                if (len < max) {
                    throw new RuntimeException("Content length mismatch");
                }
                RequestUtil.parseParameters(results, buf, encoding);
            } catch (UnsupportedEncodingException ue) {
                ;
            } catch (IOException e) {
                throw new RuntimeException("Content read fail");
            }
        }

        // Store the final results
        results.setLocked(true);
        parsed = true;
        parameters = results;
    }

    public void addCookie(Cookie cookie) {
        synchronized (cookies) {
            cookies.add(cookie);
        }
    }

    /**
     * Create and return a ServletInputStream to read the content
     * associated with this Request.  The default implementation creates an
     * instance of RequestStream associated with this request, but this can
     * be overridden if necessary.
     *
     * @throws IOException if an input/output error occurs
     */
    public ServletInputStream createInputStream() throws IOException {
        return (new RequestStream(this));
    }

    public InputStream getStream() {
        return input;
    }

    public void setContentLength(int length) {
        this.contentLength = length;
    }

    public void setContentType(String type) {
        this.contentType = type;
    }

    public void setInet(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }

    public void setContextPath(String path) {
        if (path == null)
            this.contextPath = "";
        else
            this.contextPath = path;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setPathInfo(String path) {
        this.pathInfo = path;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    /**
     * Set the name of the server (virtual host) to process this request.
     *
     * @param name The server name
     */
    public void setServerName(String name) {
        this.serverName = name;
    }

    /**
     * Set the port number of the server to process this request.
     *
     * @param port The server port
     */
    public void setServerPort(int port) {
        this.serverPort = port;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * Set a flag indicating whether or not the requested session ID for this
     * request came in through a cookie.  This is normally called by the
     * HTTP Connector, when it parses the request headers.
     *
     * @param flag The new flag
     */
    public void setRequestedSessionCookie(boolean flag) {
        this.requestedSessionCookie = flag;
    }

    public void setRequestedSessionId(String requestedSessionId) {
        this.requestedSessionId = requestedSessionId;
    }

    public void setRequestedSessionURL(boolean flag) {
        requestedSessionURL = flag;
    }

    /* implementation of the HttpServletRequest*/
    @Override
    public Object getAttribute(String name) {
        synchronized (attributes) {
            return (attributes.get(name));
        }
    }

    @Override
    public Enumeration getAttributeNames() {
        synchronized (attributes) {
            return (new Enumerator(attributes.keySet()));
        }
    }

    @Override
    public String getAuthType() {
        return null;
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public int getContentLength() {
        return contentLength;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public String getContextPath() {
        return contextPath;
    }

    @Override
    public Cookie[] getCookies() {
        synchronized (cookies) {
            if (cookies.size() < 1)
                return (null);
            Cookie results[] = new Cookie[cookies.size()];
            return ((Cookie[]) cookies.toArray(results));
        }
    }

    @Override
    public long getDateHeader(String name) {
        String value = getHeader(name);
        if (value == null)
            return (-1L);

        // Work around a bug in SimpleDateFormat in pre-JDK1.2b4
        // (Bug Parade bug #4106807)
        value += " ";

        // Attempt to convert the date header in a variety of formats
        for (int i = 0; i < formats.length; i++) {
            try {
                Date date = formats[i].parse(value);
                return (date.getTime());
            } catch (ParseException e) {
                ;
            }
        }
        throw new IllegalArgumentException(value);
    }

    @Override
    public String getHeader(String name) {
        name = name.toLowerCase();
        synchronized (headers) {
            List<String> values = headers.get(name);
            if (values != null)
                return values.get(0);
            else
                return null;
        }
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        synchronized (headers) {
            return new Enumerator<>(headers.keySet());
        }
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        name = name.toLowerCase();
        synchronized (headers) {
            List<String> values = headers.get(name);
            if (values != null)
                return (new Enumerator<>(values));
            else
                return (new Enumerator<>(empty));
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (reader != null)
            throw new IllegalStateException("getInputStream has been called");

        if (stream == null)
            stream = createInputStream();
        return (stream);
    }

    @Override
    public int getIntHeader(String name) {
        String value = getHeader(name);
        if (value == null)
            return (-1);
        else
            return (Integer.parseInt(value));
    }

    @Override
    public Locale getLocale() {
        return null;
    }

    @Override
    public Enumeration<Locale> getLocales() {
        return null;
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public String getParameter(String name) {
        parseParameters();
        String[] values = (String[]) parameters.get(name);
        if (values != null)
            return (values[0]);
        else
            return (null);
    }

    @Override
    public Map getParameterMap() {
        parseParameters();
        return (this.parameters);
    }

    @Override
    public Enumeration<String> getParameterNames() {
        parseParameters();
        return (new Enumerator<>(parameters.keySet()));
    }

    @Override
    public String[] getParameterValues(String name) {
        parseParameters();
        return parameters.get(name);
    }

    @Override
    public String getPathInfo() {
        return pathInfo;
    }

    @Override
    public String getPathTranslated() {
        return null;
    }

    @Override
    public String getProtocol() {
        return protocol;
    }

    @Override
    public String getQueryString() {
        return queryString;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        if (stream != null)
            throw new IllegalStateException("getInputStream has been called.");
        if (reader == null) {
            String encoding = getCharacterEncoding();
            if (encoding == null)
                encoding = "ISO-8859-1";
            InputStreamReader isr =
                    new InputStreamReader(createInputStream(), encoding);
            reader = new BufferedReader(isr);
        }
        return (reader);
    }

    @Override
    public String getRealPath(String path) {
        return null;
    }

    @Override
    public String getRemoteAddr() {
        return null;
    }

    @Override
    public String getRemoteHost() {
        return null;
    }

    @Override
    public String getRemoteUser() {
        return null;
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        return null;
    }

    @Override
    public String getScheme() {
        return null;
    }

    @Override
    public String getServerName() {
        return null;
    }

    @Override
    public int getServerPort() {
        return 0;
    }

    @Override
    public String getRequestedSessionId() {
        return null;
    }

    @Override
    public String getRequestURI() {
        return requestURI;
    }

    @Override
    public StringBuffer getRequestURL() {
        return null;
    }

    @Override
    public HttpSession getSession() {
        return null;
    }

    @Override
    public HttpSession getSession(boolean create) {
        return null;
    }

    @Override
    public String getServletPath() {
        return null;
    }

    @Override
    public Principal getUserPrincipal() {
        return null;
    }

    @Override
    public boolean isRequestedSessionIdFromCookie() {
        return false;
    }

    @Override
    public boolean isRequestedSessionIdFromUrl() {
        return isRequestedSessionIdFromURL();
    }

    @Override
    public boolean isRequestedSessionIdFromURL() {
        return false;
    }

    @Override
    public boolean isRequestedSessionIdValid() {
        return false;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public boolean isUserInRole(String role) {
        return false;
    }

    @Override
    public void removeAttribute(String attribute) {
    }

    @Override
    public void setAttribute(String key, Object value) {
    }

    /**
     * Set the authorization credentials sent with this request.
     *
     * @param authorization The new authorization credentials
     */
    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    @Override
    public void setCharacterEncoding(String encoding) throws UnsupportedEncodingException {
    }
}
