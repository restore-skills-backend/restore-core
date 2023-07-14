package com.restore.core.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
public class TenantContextHolder extends AppService {

    private static ThreadLocal<String> CONTEXT = new InheritableThreadLocal <>();

    public void setTenantId(String tenant) {
        CONTEXT.set(tenant);
    }

    private HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes == null) return null;
        return attributes.getRequest();
    }

    public String getTenant() {
//        String schema = CONTEXT.get();
        HttpServletRequest request = getCurrentRequest();
        if(request!=null) {
            String schema = request.getHeader("X-TENANT-ID");
            if(schema!=null && !schema.isEmpty()) {
                return schema.toLowerCase();
            }
            else {
                try{
                    return getCurrentUser().getTenantKey();
                } catch (Exception e) {
                    log.error("Failed to find schema name from current user");
                    return null;
                }
            }
        }
        else{
            try{
                return getCurrentUser().getTenantKey();
            } catch (Exception e) {
                log.error("Failed to find schema name from current user");
                return null;
            }
        }
    }

    public void clear() {
        CONTEXT.remove();
    }
}
