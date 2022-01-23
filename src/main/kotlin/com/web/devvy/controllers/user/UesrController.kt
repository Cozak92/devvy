package com.web.devvy.controllers.user

import com.web.devvy.Dto.User.UserDto.UserRequest
import com.web.devvy.model.User
import com.web.devvy.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("user")
class UesrController {

    @Autowired
    private lateinit var userRepository: UserRepository

    @PostMapping
    fun postUser(@RequestBody @Valid userRequest: UserRequest): String{
        userRepository.save(User(userRequest))
        return "Valid Test"
    }

}