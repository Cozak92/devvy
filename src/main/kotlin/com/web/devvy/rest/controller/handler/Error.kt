package com.web.devvy.rest.controller.handler

data class Error(
    var field: String? = null,
    var message: String? = null,
    var value: Any? = null
)