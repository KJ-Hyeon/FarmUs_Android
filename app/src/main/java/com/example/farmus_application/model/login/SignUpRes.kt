package com.example.farmus_application.model.login

data class SignUpRes(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: SignUpResult
)