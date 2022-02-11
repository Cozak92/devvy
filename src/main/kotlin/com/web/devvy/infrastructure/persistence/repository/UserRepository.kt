package com.web.devvy.infrastructure.persistence.repository

import com.web.devvy.infrastructure.persistence.entity.User
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): User?
    @EntityGraph(attributePaths = ["authorities"])
    fun findOneWithAuthoritiesByUsername(name: String): User?
}