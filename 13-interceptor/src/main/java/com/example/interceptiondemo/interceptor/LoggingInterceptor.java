package com.example.interceptiondemo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler)
            throws Exception {

//        if(handler instanceof HandlerMethod handlerMethod) {
//            String controllerName = handlerMethod.getBeanType().getName();
//            String methodName = handlerMethod.getMethod().getName();
//
//            System.out.println("PreHandle called");
//            System.out.println("Controller name: " + controllerName);
//            System.out.println("Method name: " + methodName);
//        }

        System.out.println("Incoming Request ------");

        System.out.println("Http Method: " + request.getMethod());
        System.out.println("Http Endpoint(URI): " + request.getRequestURI());
        System.out.println("Request Parameter: " + request.getQueryString());
        System.out.println("Client IP: " + request.getRemoteAddr());
        System.out.println("Token Header: " + request.getHeader("token"));

        if(handler instanceof HandlerMethod handlerMethod) {
            System.out.println("Controller: " + handlerMethod.getBeanType().getName());
            System.out.println("Controller Method: " + handlerMethod.getMethod().getName());
        }

        return true;
    }

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                @Nullable Exception ex)
            throws Exception {

        System.out.println("Response Status: " + response.getStatus());
    }
}
