package com.example.login.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status_code")
    val statusCode: Int?,

    @SerializedName("status")
    val status: Boolean?,

    @SerializedName("message")
    val message: String?,

    @SerializedName("data")
    val data: LoginDataModel?,
)

data class LoginDataModel(
    @SerializedName("_id")
    var staffId: String?,

    @SerializedName("email")
    var email: String?,

   @SerializedName("tokens")
   val tokens: LoginTokenModel?,

    @SerializedName("superAdmin")
    val superAdmin: Boolean?,
)
data class LoginTokenModel(
    @SerializedName("access")
    val accessToken: TokenModel?,
    @SerializedName("refresh")
    val refreshToken: TokenModel?,
)

data class TokenModel(
    @SerializedName("token")
    val token: String?,
    @SerializedName("expires")
    val expires: String?,
)
