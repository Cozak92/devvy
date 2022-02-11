package com.web.devvy.user.service

import com.ninjasquad.springmockk.MockkBean
import com.web.devvy.application.service.user.UserPortImpl
import com.web.devvy.infrastructure.persistence.entity.Authority
import com.web.devvy.infrastructure.persistence.entity.User
import com.web.devvy.infrastructure.persistence.repository.UserRepositoryPort
import com.web.devvy.rest.controller.exceptions.DuplicateMemberException
import com.web.devvy.rest.model.user.UserDto
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldStartWith
import io.mockk.every
import io.mockk.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.context.ContextConfiguration
import java.util.*

@ContextConfiguration(classes = [(UserPortImpl::class)])
class UserServiceTest : BehaviorSpec() {
    override fun extensions() = listOf(SpringExtension)

    @Autowired
    lateinit var userServiceImpl: UserPortImpl

    @MockkBean
    lateinit var passwordEncoder: PasswordEncoder

    @MockkBean
    lateinit var userRepositoryPort: UserRepositoryPort

    init {
        given("유저 가입 요청이 들어왔을 때") {
            val userJoinRequest = UserDto.UserJoinRequest(
                name = "신승혁",
                password = "1234",
                username = "cozak",
                email = "cozak92@gmail.com",
                is_deleted = false
            )
            `when`("UserService의 signup을 호출하면") {
                val authority = Collections.singleton(Authority("ROLE_USER"))
                every { passwordEncoder.encode(any()) } returns "1"
                every { userRepositoryPort.findOneWithAuthoritiesByUsername(any()) } returns null
                every { userRepositoryPort.save(any()) } returns User(
                    name = userJoinRequest.name!!,
                    password = passwordEncoder.encode(userJoinRequest.password!!),
                    email = userJoinRequest.email!!,
                    username = userJoinRequest.username!!,
                    is_deleted = false,
                    authorities = authority
                )

                val response = userServiceImpl.signup(userJoinRequest)
                then("UserResponse를 리턴한다.") {

                    verify(exactly = 1) { userRepositoryPort.findOneWithAuthoritiesByUsername(any()) }
                    verify(exactly = 1) { userRepositoryPort.save(any()) }

                    response shouldBe UserDto.UserResponse.from(
                        User(
                            name = "신승혁",
                            password = "1234",
                            username = "cozak",
                            email = "cozak92@gmail.com",
                            is_deleted = false,
                            authorities = authority
                        )
                    )
                }
            }
            `when`("이미 유저가 있다면") {
                val authority = Collections.singleton(Authority("ROLE_USER"))
                every { passwordEncoder.encode(any()) } returns "1"
                every { userRepositoryPort.findOneWithAuthoritiesByUsername(any()) } returns User(
                    name = "신승혁",
                    password = "1234",
                    username = "cozak",
                    email = "cozak92@gmail.com",
                    is_deleted = false,
                    authorities = authority
                )
                every { userRepositoryPort.save(any()) } returns User(
                    name = userJoinRequest.name!!,
                    password = passwordEncoder.encode(userJoinRequest.password!!),
                    email = userJoinRequest.email!!,
                    username = userJoinRequest.username!!,
                    is_deleted = false,
                    authorities = authority
                )

                then("DuplicateMemberException을 예외로 던진다.") {
                    val exception = shouldThrowExactly<DuplicateMemberException> {
                        userServiceImpl.signup(userJoinRequest)
                    }
                    verify(exactly = 1) { userRepositoryPort.findOneWithAuthoritiesByUsername(any()) }
                    verify(exactly = 0) { userRepositoryPort.save(any()) }

                    exception.message shouldBe ("이미 가입되어 있는 유저입니다.")
                }
            }
        }

        given("어드민이 username으로 정보를 요청 했을때") {
            val username = "roo333"
            `when`("유저를 찾았다면") {
                val authority = Collections.singleton(Authority("ROLE_USER"))
                every { userRepositoryPort.findOneWithAuthoritiesByUsername(any()) } returns User(
                    name = "신승혁",
                    password = "1234",
                    username = "roo333",
                    email = "cozak92@gmail.com",
                    is_deleted = false,
                    authorities = authority
                )
                then("유저 정보를 리턴한다.") {
                    val response = userServiceImpl.getUserWithAuthorities(username)
                    response shouldBe UserDto.UserResponse.from(
                        User(
                            name = "신승혁",
                            password = "1234",
                            username = "roo333",
                            email = "cozak92@gmail.com",
                            is_deleted = false,
                            authorities = authority
                        )
                    )
                    verify(exactly = 1) { userRepositoryPort.findOneWithAuthoritiesByUsername(any()) }
                }
            }
            `when`("유저를 못찾았다면") {
                every { userRepositoryPort.findOneWithAuthoritiesByUsername(any()) } returns null
                then("UsernameNotFoundException을 예외로 던진다.") {
                    val exception = shouldThrowExactly<UsernameNotFoundException> {
                        userServiceImpl.getUserWithAuthorities(username)
                    }
                    exception.message shouldStartWith ("roo333")
                    verify(exactly = 1) { userRepositoryPort.findOneWithAuthoritiesByUsername(any()) }
                }
            }
        }
    }
}


