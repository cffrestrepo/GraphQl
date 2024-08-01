package edu.unisabana.graphql.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement


@Configuration
@EntityScan("edu.unisabana.graphql.domain")
@EnableJpaRepositories("edu.unisabana.graphql.repos")
@EnableTransactionManagement
class DomainConfig
