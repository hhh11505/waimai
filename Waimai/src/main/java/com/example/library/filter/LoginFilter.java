package com.example.library.filter;


import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
        HttpServletRequest hsr = (HttpServletRequest) req;
        HttpServletResponse hsp = (HttpServletResponse) res;
        String projectName = hsr.getContextPath();
        String url = hsr.getRequestURI();
//        System.out.println("projectName"+projectName);
//        System.err.println("url1"+url);
//        System.out.println(url.equals(projectName+"/admin/login"));
//        System.out.println(url.equals(projectName+"/admin/dologin"));
        if(url.contains("style")){
            chain.doFilter(hsr, hsp);
        }
        if(url.equals(projectName+"/admin/login")||url.equals(projectName+"/admin/dologin")||url.equals(projectName+"/admin/regist")||url.equals(projectName+"/admin/toregist")) {
            chain.doFilter(hsr, hsp);
            return;
        }else {
            String username = (String) hsr.getSession().getAttribute("username");
//            System.out.println(username);
            if(username!=null) {
//                System.err.println("url2"+url);
                chain.doFilter(hsr, hsp);
                return;
            }else {
                hsp.sendRedirect(projectName+"/admin/login");

            }
        }
    }

    @Override
    public void destroy() {

    }
}

