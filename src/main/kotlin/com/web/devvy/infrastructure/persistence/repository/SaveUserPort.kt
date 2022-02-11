package com.web.devvy.infrastructure.persistence.repository

import com.web.devvy.domain.entity.User

interface SaveUserPort {
    fun findByEmail(email: String): User?
    fun save(user: User): User
}