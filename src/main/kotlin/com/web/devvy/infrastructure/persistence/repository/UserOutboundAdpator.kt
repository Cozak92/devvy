package com.web.devvy.infrastructure.persistence.repository

import com.web.devvy.domain.entity.User

class UserOutboundAdpator(private val importedUserJpaRepository: ImportedUserJpaRepository): SaveUserPort, GetAuthoritiesPort {
    override fun findByEmail(email: String): User? {
        return importedUserJpaRepository.findByEmail(email)
    }

    override fun findOneWithAuthoritiesByUsername(name: String): User? {
        return importedUserJpaRepository.findOneWithAuthoritiesByUsername(name)
    }

    override fun save(user: User): User {
        return importedUserJpaRepository.save(user)
    }
}