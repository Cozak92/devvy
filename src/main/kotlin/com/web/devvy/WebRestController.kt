package com.web.devvy

import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequiredArgsConstructor
class WebRestController {

    @Autowired
    private lateinit var env: Environment

    @GetMapping("/profile")
    fun getProfile(): String{
        return Arrays.stream(env.activeProfiles).findFirst().orElse("")
    }
}