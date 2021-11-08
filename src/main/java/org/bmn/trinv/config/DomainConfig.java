package org.bmn.trinv.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("org.bmn.trinv.domain")
@EnableJpaRepositories("org.bmn.trinv.repos")
@EnableTransactionManagement
public class DomainConfig {
}
