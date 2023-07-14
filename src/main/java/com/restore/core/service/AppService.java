package com.restore.core.service;

import com.restore.core.exception.RestoreSkillsException;
import com.restore.core.dto.app.User;
import com.restore.core.dto.response.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;

import java.util.Map;

@Slf4j
public abstract class AppService {

    @Value("${keycloak.client-id}")
    private String clientId;

    protected void throwError(ResponseCode code, String message) throws RestoreSkillsException {
        log.error("Custom Exception : [{}] {}", code, message);
        throw new RestoreSkillsException(code, message);
    }

    protected void throwError(Exception exception) throws RestoreSkillsException {
        log.error("Handled Exception : {}", exception.getMessage());
        throwError(new RestoreSkillsException(exception));
    }

    protected User getCurrentSchema(String tenantKey) throws RestoreSkillsException {
         User user = getCurrentUser();
         user.setTenantKey(tenantKey);
         return user;
    }

    protected static User getCurrentUser() throws RestoreSkillsException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        OAuth2IntrospectionAuthenticatedPrincipal principal = (OAuth2IntrospectionAuthenticatedPrincipal) auth.getPrincipal();
        Map<String, Object> attributes = principal.getAttributes();
        Map<String, Object> resourceAccess = (Map<String, Object>) attributes.get("resource_access");

//        Map<String, Object> clientRole = (Map<String, Object>) resourceAccess.get(clientId);
//        List<String> roles = (List<String>) clientRole.get("roles");

        log.info("Current User : {}", attributes);

        return User.builder()
                .firstName(attributes.containsKey("given_name") ? attributes.get("given_name").toString() : null)
                .lastName(attributes.containsKey("family_name") ? attributes.get("family_name").toString() : null)
                .email(attributes.containsKey("email") ? attributes.get("email").toString() : null)
                .emailVerified(Boolean.parseBoolean(attributes.containsKey("email_verified") ? attributes.get("email_verified").toString() : "false"))
                .active(Boolean.parseBoolean(attributes.containsKey("active") ? attributes.get("active").toString() : "false"))
//                .role( !roles.isEmpty() ? Roles.valueOf(roles.get(0)) : Roles.ANONYMOUS)
                .tenantKey(attributes.containsKey("groups") ? attributes.get("groups").toString() : null)
                .iamId(attributes.containsKey("sub") ? attributes.get("sub").toString() : null)
                .build();
    }
}