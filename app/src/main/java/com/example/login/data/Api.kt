package com.example.login.data
import com.example.login.model.*
import com.example.login.ui.VerifyOtpActivity
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface Api {

    @POST("v1/digiclass/user/authLogin")
    fun login(
        @Body credentials: CredentialModel,
    ): Call<LoginResponse?>


    @Multipart
    @POST("v1/digiclass/user/forget")
    fun requestOtp(
        @Part("email") email: RequestBody,
        @Part("validation_mode") mode: RequestBody,
        @Part("user_type") userType: RequestBody,
    ): Call<RequestOtpResponse?>


    @POST("v1/digiclass/user/otp_verify")
    fun verifyOtp(
        @Body verifyOtp: VerifyOtpRequest,
    ): Call<VerifyOtpModel>

    @POST("v1/digiclass/user/set_password")
    fun changePassword(
        @Body changePasswordModel: ChangePasswordRequest,
    ):Call<ChangePasswordModel?>

    @GET("v1/digiclass/notification/{userId}")
    fun getNotifications(
        @Path("userId") userId: String?,
        @Query("limit") limit: Int? = 10,
        @Query("pageNo") pageNumber: Int = 1,
    ): Call<NotificationResponse?>

    @GET("v1/digiclass/document/{userId}")
    fun getRecentDocumentList(
        @Path("userId") userId: String?,
        @Query("limit") limit: Int?,
        @Query("pageNo") pageNumber: Int?,
        @Query("tab") isStarred: Boolean? = null,
    ): Call<DocumentListResponse?>
}