package com.example.login.model

import com.google.gson.annotations.SerializedName

data class RequestOtpDataModel(
    @SerializedName("_id")
    val id: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("mobile")
    val mobile: String?,
)

data class VerifyOtpRequest(
    @SerializedName("_id")
    var id: String? = null,
    @SerializedName("otp")
    var otp: String? = null,
)

data class ChangePasswordRequest(
    @SerializedName("_id")
    val verificationId: String,
    @SerializedName("new_password")
    val password: String?,
)

data class ChangePasswordModel(
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: ChangePasswordDataModel?,
    @SerializedName("error")
    val error: ApiErrorModel?,
)


data class ChangePasswordDataModel(
    @SerializedName("_id")
    val verificationId: String,
    /*@SerializedName("name")
    val name: FaceNameModel,*/
    @SerializedName("email")
    val email: String?,
    @SerializedName("user_type")
    val userType: String?,
    @SerializedName("biometric_data")
    val userImage: String?,
)