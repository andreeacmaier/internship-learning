package com.arobs.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class RequestFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(RequestFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        LOGGER.info("Request - URI: " + req.getRequestURI() + "  Remote Address: " + req.getRemoteAddr() + "  Response status = " + resp.getStatus());
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
