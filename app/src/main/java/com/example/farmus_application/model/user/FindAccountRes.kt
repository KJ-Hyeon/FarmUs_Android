package com.example.farmus_application.model.user

import kotlinx.serialization.Serializable

@Serializable
data class FindAccountRes(
    val email: String
)