package com.example.__fillters_demo.filters;


import jakarta.servlet.*;

import java.io.IOException;

public class DummyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        System.out.println("Dummy filter called");

        chain.doFilter(request, response);
    }
}
