package com.jincheng.customer.interceptor;

import com.jincheng.customer.bean.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;

//拦截器 pre controller post after
//springmvc xml配置
//配置类
@Component
public class TestInterceptor implements HandlerInterceptor {
    //    @Value("${work.open}")
//    String open;
//    @Value("${work.close}")
//    String close;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //false: 不处理 拦截 true：进入处理器 放行
        //拦截所有请求中带有test的请求
        System.out.println(request.getRequestURL());
        HttpSession session = request.getSession();
//        session.setAttribute("loginUser",new User());
        User user = (User) session.getAttribute("loginUser");
//        String product="|| request.getRequestURL().indexOf(\"product\") != -1";
        if (request.getRequestURL().indexOf("customer") != -1
                || request.getRequestURL().indexOf("product") != -1
                || request.getRequestURL().indexOf("order") != -1) {
            if (user == null) {
                return false;
            }
            return true;
        }
        return true;
    }
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("post");
//    }
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
//        System.out.println("after");
//    }
}
