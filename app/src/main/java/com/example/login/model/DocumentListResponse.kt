package com.example.login.model

import com.google.gson.annotations.SerializedName

data class DocumentListResponse(
    @SerializedName("status_code")
    val statusCode: Int?,
    @SerializedName("status")
    val status: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("totalDoc")
    val totalDoc: Int?,
    @SerializedName("totalPages")
    val totalPages: Int?,
    @SerializedName("currentPage")
    val currentPage: Int?,
    @SerializedName("data")
    val documentList: ArrayList<DocumentItem>?,
)

data class DocumentCourseDetail(
    @SerializedName("_id")
    val id: String?,
    @SerializedName("_course_id")
    val courseId: String?,
    @SerializedName("course_name")
    val courseName: String?,
    @SerializedName("course_code")
    val courseCode: String?,
)

