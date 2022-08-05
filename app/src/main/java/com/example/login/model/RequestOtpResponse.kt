package com.example.login.model

import com.google.gson.annotations.SerializedName

data class RequestOtpResponse(
    @SerializedName("status_code")
    val statusCode: Int?,

    @SerializedName("status")
    val status: Boolean?,

    @SerializedName("message")
    val message: String?,

    @SerializedName("data")
    val data: RequestOtpDataModel?,
)