package com.example.__fillters_demo.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

//@Component
public class ResponseBodyFIlter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        ContentCachingResponseWrapper wrappedResponse =
                new ContentCachingResponseWrapper(httpServletResponse);

        chain.doFilter(request, wrappedResponse);

        byte[] originalBodyBytes = wrappedResponse.getContentAsByteArray();

        String originalBody = new String(originalBodyBytes);

        String modifiedBody = """
                {
                   "originalResponse" : %s,
                   "appName" : "Student Application"
                }
                """.formatted(originalBody);

        wrappedResponse.resetBuffer();

        wrappedResponse.getWriter().write(modifiedBody);

        wrappedResponse.copyBodyToResponse();
    }
}
