package com.web.devvy.infrastructure.persistence.repository

import com.web.devvy.infrastructure.persistence.entity.User

interface UserRepositoryPort {
    fun findByEmail(email: String): User?
    fun findOneWithAuthoritiesByUsername(name: String): User?
    fun save(user: User): User
}