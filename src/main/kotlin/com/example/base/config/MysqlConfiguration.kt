package com.example.base.config

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import java.time.Instant
import javax.sql.DataSource

@Profile("mysql")
@Configuration
@EnableJpaRepositories(
    basePackages = ["com.example.base.repository"],
    entityManagerFactoryRef = "entityManagerFactory",
    transactionManagerRef = "transactionManager"
)
class MysqlConfiguration {

    private val log = KotlinLogging.logger {}

    /**
     * Data source
     * 데이터 베이스 빈 <br/>
     * @return DataSource
     */
    @Bean("dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    fun dataSource(): DataSource {
        log.info { "DataSource Bean|status=SUCCESS|time=${Instant.now()}" }
        return DataSourceBuilder.create().build()
    }

    /**
     * Jpa properties
     * jpa setting bean <br/>
     * @return JpaProperties
     */
    @Bean("jpaProperties")
    @ConfigurationProperties(prefix = "spring.jpa")
    fun jpaProperties(): JpaProperties {
        log.info { "JpaProperties Bean|status=SUCCESS|time=${Instant.now()}" }
        return JpaProperties()
    }

    /**
     * Entity manager factory Bean <br/>
     *
     * @param builder EntityManagerFactoryBuilder
     * @return LocalContainerEntityManagerFactoryBean
     */
    @Bean("entityManagerFactory")
    fun entityManagerFactory(builder: EntityManagerFactoryBuilder): LocalContainerEntityManagerFactoryBean {
        return builder.dataSource(dataSource())
            .properties(jpaProperties().properties)
            .packages("com.example.base.domain")
            .persistenceUnit("default")
            .build()
    }

    /**
     * Transaction manager Bean</br>
     *
     * @param entityManagerFactory LocalContainerEntityManagerFactoryBean
     * @return PlatformTransactionManager
     */
    @Bean("transactionManager")
    fun transactionManager(@Qualifier("entityManagerFactory") entityManagerFactory: LocalContainerEntityManagerFactoryBean): PlatformTransactionManager {
        var jpaTransactionManager = entityManagerFactory.`object`?.let { JpaTransactionManager(it) }
        jpaTransactionManager!!.isNestedTransactionAllowed = true
        return jpaTransactionManager
    }

}
