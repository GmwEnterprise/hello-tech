// 用于测试servlet容器的servlet实例

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class PrimitiveServlet implements Servlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("primitive servlet init.");
    }

    @Override
    public void destroy() {
        System.out.println("primitive servlet destroy.");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("from service");
        PrintWriter writer = res.getWriter();
        writer.println("Hello. Roses are red.");
        writer.print("Violets are blue.");
        writer.flush();
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }
}
