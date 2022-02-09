package com.web.devvy.repository

import com.web.devvy.entity.User

interface UserRepositoryPort {
    fun findByEmail(email: String): User?
    fun findOneWithAuthoritiesByUsername(name: String): User?
    fun save(user: User): User
}