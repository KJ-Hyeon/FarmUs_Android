package com.example.farmus_application.model.user.findpassword

import com.google.gson.annotations.SerializedName

data class FindPasswordRes(
    @SerializedName("result") val result: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)