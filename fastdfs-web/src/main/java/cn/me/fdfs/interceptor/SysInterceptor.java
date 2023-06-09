package cn.me.fdfs.interceptor;

import com.mysql.cj.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: LZS
 * Date: 11-12-9
 * Time: 上午10:04
 */
public class SysInterceptor extends HandlerInterceptorAdapter {
    private static Logger logger = LoggerFactory.getLogger(SysInterceptor.class);
    private static String contentPath = "";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getContextPath();
        System.out.println("=========================>:" + path);
        //if (StringUtils.isEmptyOrWhitespaceOnly(path)) {
        //    path = "/";
        //}
        request.setAttribute("basePath", path);
        contentPath = path;
        StringBuffer requestpath = request.getRequestURL();

        if (requestpath.indexOf("login") == -1) {
            Object name = request.getSession().getAttribute("username");
            if (name == null) {
                response.sendRedirect(path + "main/login.shtml");
            }
        }
        //buildMail.send("service@vivame.cn","VivaMe维我", "5515068@qq.com", "", "系统日志", request.getRemoteAddr() +" " +handler.getClass().getName(), null);
        System.out.println(request.getRemoteAddr() + " " + handler.getClass().getName());
        return true;
    }
}