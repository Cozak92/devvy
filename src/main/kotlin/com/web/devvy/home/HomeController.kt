package com.web.devvy.home

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

   @GetMapping("/")
   fun home(): String {
       Thread.sleep(60000)
       return "hello"
   }
}