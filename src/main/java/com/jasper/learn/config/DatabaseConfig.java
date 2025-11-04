package com.jasper.learn.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.jasper.learn.adapter.repository")
@EntityScan(basePackages = "com.jasper.learn.domain.entity")
@EnableTransactionManagement
public class DatabaseConfig {
}
