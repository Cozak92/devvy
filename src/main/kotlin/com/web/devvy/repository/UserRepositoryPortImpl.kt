package com.web.devvy.repository

import com.web.devvy.entity.User
import com.web.devvy.repository.UserRepository

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