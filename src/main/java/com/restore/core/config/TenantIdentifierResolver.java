package com.restore.core.config;

import com.restore.core.service.AppService;
import com.restore.core.service.TenantContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class TenantIdentifierResolver extends AppService implements CurrentTenantIdentifierResolver, HibernatePropertiesCustomizer {

    private static final String DEFAULT_TENANT_ID = "public";
    @Override
    public String resolveCurrentTenantIdentifier() {
        try {
            String tenant = new TenantContextHolder().getTenant();
            log.info("Requested tenant : {}", tenant);
            return tenant!=null && !tenant.equalsIgnoreCase("") ? tenant : DEFAULT_TENANT_ID;
        } catch (Exception e) {
            return DEFAULT_TENANT_ID;
        }
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }

    @Override
    public boolean isRoot(String tenantId) {
        return CurrentTenantIdentifierResolver.super.isRoot(tenantId);
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, this);
    }
}
