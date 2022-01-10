package com.web.devvy.home

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class HomeController {

    @Autowired
    private lateinit var env: Environment

   @GetMapping("/")
   fun home(): String {
       val x = 1
       Thread.sleep(120000)
       return "hello" + Arrays.stream(env.activeProfiles).findFirst().orElse("")
   }
}