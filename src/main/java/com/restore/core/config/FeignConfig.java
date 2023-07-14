package com.restore.core.config;


import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.hc.core5.http.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class FeignConfig {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Bean
    public RequestInterceptor requestInterceptor() {

        return requestTemplate -> {
            requestTemplate.header("Authorization", ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getHeader("Authorization"));
            requestTemplate.header("Accept", ContentType.APPLICATION_JSON.getMimeType());
        };
    }

}
