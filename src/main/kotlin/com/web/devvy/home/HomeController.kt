package com.web.devvy.home

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

   @GetMapping("/")
   fun home(): String {
       val x = 1
       Thread.sleep(120000)
       return "hello"
   }
}