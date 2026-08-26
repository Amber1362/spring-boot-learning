package com.example.__fillters_demo.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@Order(2)
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        Long startTime = System.currentTimeMillis();

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestId = UUID.randomUUID().toString();

        httpResponse.setHeader("Request-ID", requestId);

        System.out.println("Incoming Request: "
                + httpRequest.getMethod() + " "
                + httpRequest.getRequestURI());

        try {
            chain.doFilter(request, response);
        } finally {
            long duration = System.currentTimeMillis() - startTime;

            System.out.println("Response Status: "
                    + httpResponse.getStatus());

            System.out.println("API Response time : " + duration);
        }
    }
}
