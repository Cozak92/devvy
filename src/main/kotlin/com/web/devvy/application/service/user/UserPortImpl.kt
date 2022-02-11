package com.web.devvy.application.service.user

import com.web.devvy.rest.model.user.UserDto.*
import com.web.devvy.infrastructure.persistence.entity.Authority
import com.web.devvy.infrastructure.persistence.entity.User
import com.web.devvy.rest.controller.exceptions.DuplicateMemberException
import com.web.devvy.infrastructure.persistence.repository.UserRepositoryPort
import com.web.devvy.infrastructure.utils.SecurityUtil
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
class UserPortImpl(private val userRepository: UserRepositoryPort, private val passwordEncoder: PasswordEncoder) :
    UserPort {
    @Transactional
    override fun signup(UserJoinRequest: UserJoinRequest): UserResponse? {
        if (userRepository.findOneWithAuthoritiesByUsername(UserJoinRequest.username!!) != null) {
            throw DuplicateMemberException("이미 가입되어 있는 유저입니다.")
        }
        val authority = Authority("ROLE_USER")
        val user = User(
                name = UserJoinRequest.name!!,
                password = passwordEncoder.encode(UserJoinRequest.password!!),
                email = UserJoinRequest.email!!,
                username = UserJoinRequest.username,
                authorities = Collections.singleton(authority),
                is_deleted = false,
        )

        return UserResponse.from(userRepository.save(user))
    }

    @Transactional(readOnly = true)
    override fun getUserWithAuthorities(userName: String): UserResponse? {
        return UserResponse.from(userRepository.findOneWithAuthoritiesByUsername(userName)
                ?: throw UsernameNotFoundException("$userName -> 데이터베이스에서 찾을 수 없습니다."))
    }

    @Transactional(readOnly = true)
    override fun getMyUserWithAuthorities(): UserResponse? {
        return UserResponse.from(SecurityUtil.currentUsername?.let {
            userRepository.findOneWithAuthoritiesByUsername(
                    it
            )
        })
    }
}
