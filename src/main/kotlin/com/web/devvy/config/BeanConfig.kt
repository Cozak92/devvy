package com.web.devvy.config

import com.web.devvy.infrastructure.persistence.repository.ImportedUserJpaRepository
import com.web.devvy.infrastructure.persistence.repository.SaveUserPort
import com.web.devvy.infrastructure.persistence.repository.UserOutboundAdpator
import com.web.devvy.application.service.user.UserService
import com.web.devvy.infrastructure.persistence.repository.GetAuthoritiesPort
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class BeanConfig {

    @Bean
    fun userCustomService(
        saveUserPort: SaveUserPort,
        getAuthoritiesPort: GetAuthoritiesPort,
        passwordEncoder: PasswordEncoder
    ): UserService {
        return UserService(saveUserPort, getAuthoritiesPort, passwordEncoder)
    }

    @Bean
    fun userOutboundAdpator(importedUserJpaRepository: ImportedUserJpaRepository): UserOutboundAdpator {
        return UserOutboundAdpator(importedUserJpaRepository)
    }
}