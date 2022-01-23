package com.web.devvy.controllers.user

import com.web.devvy.Dto.User.UserDto.UserRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("user")
class UesrController {

    @PostMapping
    fun postUser(@RequestBody @Valid UserRequest: UserRequest): String{
        return "Valid Test"
    }

    @GetMapping("/user")
    @ResponseBody
    fun getUsersList(): Array<String>{
        val user = arrayOf("신승혁,30", "최현민,30")
        return user
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    fun getOneUser(id: Int):String{
        return "신승혁,30"
    }
}