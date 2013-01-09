package cn.oneteam.dispatcher;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 初始化配置和处理所有请求。
 * @author OneTeam K
 */

public class DispatcherServlet extends GenericServlet {

    private Dispatcher dispatcher;
    private StaticFileHandler staticFileHandler;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        this.dispatcher = new Dispatcher();
        this.dispatcher.init(
                new Config() {
                    public String getInitParameter(String name) {
                        return config.getInitParameter(name);
                    }

                    public ServletContext getServletContext() {
                        return config.getServletContext();
                    }
                }
        );
        this.staticFileHandler = new StaticFileHandler(config);
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) resp;
        String method = httpReq.getMethod();
        if ("GET".equals(method) || "POST".equals(method)) {
            if (!dispatcher.service(httpReq, httpResp))
                staticFileHandler.handle(httpReq, httpResp);
            return;
        }
        httpResp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    public void destroy() {
        this.dispatcher.destroy();
    }

}
