package com.web.devvy.handler

data class Error(
    var field: String? = null,
    var message: String? = null,
    var value: Any? = null
)