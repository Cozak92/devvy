package com.web.devvy.services

import com.web.devvy.Dto.User.UserDto.*
import com.web.devvy.entity.Authority
import com.web.devvy.entity.User
import com.web.devvy.exceptions.DuplicateMemberException
import com.web.devvy.repository.UserRepository
import com.web.devvy.utils.SecurityUtil
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
class UserService(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) {
    @Transactional
    fun signup(UserJoinRequest: UserJoinRequest): UserResponse? {
        if (userRepository.findOneWithAuthoritiesByUsername(UserJoinRequest.username!!) != null) {
            throw DuplicateMemberException("이미 가입되어 있는 유저입니다.")
        }
        val authority: Authority = Authority("ROLE_USER")
        val user: User = User(
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
    fun getUserWithAuthorities(username: String): UserResponse? {
        return UserResponse.from(userRepository.findOneWithAuthoritiesByUsername(username))
    }

    @Transactional(readOnly = true)
    fun getMyUserWithAuthorities(): UserResponse? {
        println(SecurityUtil.currentUsername)
        return UserResponse.from(SecurityUtil.currentUsername?.let {
            userRepository.findOneWithAuthoritiesByUsername(
                it
            )
        })
    }
}
