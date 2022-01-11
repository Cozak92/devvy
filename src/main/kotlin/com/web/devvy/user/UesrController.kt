package com.web.devvy.user

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class UesrController {

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