package com.restore.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
Move @EnableJpaAuditing annotation to a separate @Configuration class otherwise it will be loaded even for unrelated application slices.
 */

@Configuration
@EnableJpaAuditing
public class JpaAuditingConfiguration {
}
