package com.web.devvy.application.service.user

import com.web.devvy.domain.command.user.SignUpUserCommand
import com.web.devvy.rest.model.user.UserDto.*
import com.web.devvy.domain.entity.Authority
import com.web.devvy.domain.entity.User
import com.web.devvy.domain.vo.User.Authorities
import com.web.devvy.domain.vo.User.IsDeleted
import com.web.devvy.domain.vo.User.UserPassword
import com.web.devvy.infrastructure.persistence.repository.GetAuthoritiesPort
import com.web.devvy.rest.controller.exceptions.DuplicateMemberException
import com.web.devvy.infrastructure.persistence.repository.SaveUserPort
import com.web.devvy.infrastructure.utils.SecurityUtil
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
class UserService(private val userSavePort: SaveUserPort, private val getAuthoritiesPort: GetAuthoritiesPort, private val passwordEncoder: PasswordEncoder) :
    UserPort {
    @Transactional
    override fun signup(signUpUserCommand: SignUpUserCommand): UserResponse? {

        if (getAuthoritiesPort.findOneWithAuthoritiesByUsername(signUpUserCommand.username!!.value) != null) {
            throw DuplicateMemberException("이미 가입되어 있는 유저입니다.")
        }
        val authority = Authority("ROLE_USER")
        val user = User(
                name = signUpUserCommand.name!!,
                password = UserPassword(passwordEncoder.encode(signUpUserCommand.password!!.value)),
                email = signUpUserCommand.email!!,
                username = signUpUserCommand.username,
                authorities = Collections.singleton(authority),
                isDeleted = IsDeleted(false),
        )

        return UserResponse.from(userSavePort.save(user))
    }

    @Transactional(readOnly = true)
    override fun getUserWithAuthorities(userName: String): UserResponse? {
        return UserResponse.from(getAuthoritiesPort.findOneWithAuthoritiesByUsername(userName)
                ?: throw UsernameNotFoundException("$userName -> 데이터베이스에서 찾을 수 없습니다."))
    }

    @Transactional(readOnly = true)
    override fun getMyUserWithAuthorities(): UserResponse? {
        return UserResponse.from(SecurityUtil.currentUsername?.let {
            getAuthoritiesPort.findOneWithAuthoritiesByUsername(
                    it
            )
        })
    }
}
