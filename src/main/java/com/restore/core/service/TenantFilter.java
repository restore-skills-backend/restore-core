package com.restore.core.service;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@Slf4j
public class TenantFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("TENANT: " + request.getHeader("X-TENANT-ID"));

        /* Todo: to check if the role is admin or not
                 if the role is super_admin then only user will be able to pass X-tenant-id.
         */
        Optional.ofNullable(request.getHeader("X-TENANT-ID")).map(String::toLowerCase)
                .ifPresent(schema -> new TenantContextHolder().setTenantId(schema));
        filterChain.doFilter(request, response);
    }
}
