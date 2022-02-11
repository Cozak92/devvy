package com.web.devvy.infrastructure.persistence.repository

import com.web.devvy.domain.entity.User

interface GetAuthoritiesPort {
    fun findOneWithAuthoritiesByUsername(name: String): User?
}