package com.web.devvy.services.user

import com.web.devvy.dto.user.UserDto.*
import com.web.devvy.entity.Authority
import com.web.devvy.entity.User
import com.web.devvy.exceptions.DuplicateMemberException
import com.web.devvy.repository.UserRepository
import com.web.devvy.repository.UserRepositoryPort
import com.web.devvy.utils.SecurityUtil
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
class UserPortImpl(private val userRepository: UserRepositoryPort, private val passwordEncoder: PasswordEncoder) : UserPort {
    @Transactional
    override fun signup(userJoinRequest: UserJoinRequest): UserResponse? {
        if (userRepository.findOneWithAuthoritiesByUsername(userJoinRequest.username!!) != null) {
            throw DuplicateMemberException("이미 가입되어 있는 유저입니다.")
        }
        val authority = Authority("ROLE_USER")
        val user = User(
                name = userJoinRequest.name!!,
                password = passwordEncoder.encode(userJoinRequest.password!!),
                email = userJoinRequest.email!!,
                username = userJoinRequest.username,
                authorities = Collections.singleton(authority),
                is_deleted = false,
        )

        return UserResponse.from(userRepository.save(user))
    }

    @Transactional(readOnly = true)
    override fun getUserWithAuthorities(username: String): UserResponse? {
        return UserResponse.from(userRepository.findOneWithAuthoritiesByUsername(username)
                ?: throw UsernameNotFoundException("$username -> 데이터베이스에서 찾을 수 없습니다."))
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
