package com.web.devvy.user.service

import com.ninjasquad.springmockk.MockkBean
import com.web.devvy.Dto.User.UserDto
import com.web.devvy.entity.Authority
import com.web.devvy.entity.User
import com.web.devvy.repository.UserRepository
import com.web.devvy.services.UserService
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.kotest.spring.SpringListener
import io.mockk.every
import io.mockk.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.context.ContextConfiguration
import java.util.*

@ContextConfiguration(classes = [(UserService::class)])
class UserServiceTest() : BehaviorSpec() {
    override fun extensions() = listOf(SpringExtension)

    @Autowired lateinit var userService: UserService
    @MockkBean lateinit var passwordEncoder: PasswordEncoder
    @MockkBean lateinit var userRepository:  UserRepository

        init {
            given("유저 가입 폼이 들어왔을 때") {
                val userJoinRequest = UserDto.UserJoinRequest(
                    name = "신승혁",
                    password = "1234",
                    username = "cozak",
                    email = "cozak92@gmail.com",
                    is_deleted = false
                )
                `when`("UserService의 signup을 호출하면") {
                    val authority = Collections.singleton(Authority("ROLE_USER"))
                    every { passwordEncoder.encode(any())} returns "1"
                    every { userRepository.findOneWithAuthoritiesByUsername(any()) } returns null
                    every { userRepository.save(any()) } returns User(
                        name =  userJoinRequest.name!!,
                        password = passwordEncoder.encode(userJoinRequest.password!!),
                        email =  userJoinRequest.email!!,
                        username =  userJoinRequest.username!!,
                        is_deleted = false,
                        authorities = authority
                    )


                    then("UserResponse를 리턴한다."){
                        val response = userService.signup(userJoinRequest)
                        verify(exactly = 1) {  userRepository.findOneWithAuthoritiesByUsername(any()) }
                        verify(exactly = 1) {  userRepository.save(any()) }
                        response shouldBe UserDto.UserResponse.from(User(
                            name = "신승혁",
                            password = "1234",
                            username = "cozak",
                            email = "cozak92@gmail.com",
                            is_deleted = false,
                            authorities = authority
                        ))
                    }


                }
            }
        }
}

