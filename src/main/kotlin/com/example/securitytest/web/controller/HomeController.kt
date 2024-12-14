package com.example.securitytest.web.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {

    @GetMapping("/")
    fun test(): String {
        return "home"
    }

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }
}