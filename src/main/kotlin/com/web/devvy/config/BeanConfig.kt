package com.web.devvy.config

import com.web.devvy.infrastructure.persistence.repository.UserRepository
import com.web.devvy.infrastructure.persistence.repository.UserRepositoryPort
import com.web.devvy.infrastructure.persistence.repository.UserRepositoryPortImpl
import com.web.devvy.application.service.user.UserPortImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class BeanConfig {

    @Bean
    fun userService(userRepositoryPort: UserRepositoryPort, passwordEncoder: PasswordEncoder): UserPortImpl {
        return UserPortImpl(userRepositoryPort, passwordEncoder)
    }
    @Bean
    fun userRepositoryPort(userRepository: UserRepository): UserRepositoryPortImpl {
        return UserRepositoryPortImpl(userRepository)
    }
}