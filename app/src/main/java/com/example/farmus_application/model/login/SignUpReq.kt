package com.example.farmus_application.model.login

data class SignUpReq (
    val email: String,
    val name: String,
    val nickName: String,
    val password: String,
    val phoneNumber: String,
    val role: String
)