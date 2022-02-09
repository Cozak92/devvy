package com.web.devvy.config

import com.web.devvy.repository.UserRepository
import com.web.devvy.repository.UserRepositoryPort
import com.web.devvy.repository.UserRepositoryPortImpl
import com.web.devvy.services.user.UserPortImpl
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
    fun userRepositoryPort(userRepository: UserRepository): UserRepositoryPortImpl{
        return UserRepositoryPortImpl(userRepository)
    }
}