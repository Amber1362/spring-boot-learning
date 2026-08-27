package com.example.__fillters_demo.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;

//@Component
public class RequestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

//        httpServletRequest.getInputStream();

        BufferedReader reader = httpServletRequest.getReader();

        StringBuilder body = new StringBuilder();

        String line = reader.readLine();

        while(line != null) {
            body.append(line);
            line = reader.readLine();
        }

        System.out.println(body);

        chain.doFilter(request, response);
    }
}
