package com.web.devvy.infrastructure.persistence.repository

import com.web.devvy.infrastructure.persistence.entity.User
import com.web.devvy.infrastructure.persistence.repository.UserRepository

class UserRepositoryPortImpl(private val userRepository: UserRepository): UserRepositoryPort {
    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    override fun findOneWithAuthoritiesByUsername(name: String): User? {
        return userRepository.findOneWithAuthoritiesByUsername(name)
    }

    override fun save(user: User): User {
        return userRepository.save(user)
    }
}