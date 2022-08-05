package com.example.login.model

import com.google.gson.annotations.SerializedName

data class DocumentItem(
    @SerializedName("_id")
    var documentId: String?,

    @SerializedName("type")
    val type: String?,

    @SerializedName("name")
    var name: String?,

    @SerializedName("url")
    var url: String?,

    @SerializedName("_course_id")
    val courseId: String?,

    @SerializedName("createdAt")
    val createdAt: String?,

    @SerializedName("updatedAt")
    val updatedAt: String?,

    @SerializedName("courseDetail")
    val courseDetail: DocumentCourseDetail?,

    @SerializedName("UploadedBy")
    val userDetail: DocumentUserDetail?,

    @SerializedName("starred")
    var starred: Boolean?,

    @SerializedName("courseAdmin")
    var courseAdmin: Boolean?,

    @SerializedName("sessions")
    var sessions: ArrayList<SessionForUser?>?,
    @SerializedName("createdBy")
    var createdBy: String?,
    @SerializedName("updatedDate")
    var updatedDate: String?,
    @SerializedName("sessionsName")
    var sessionsName: String?,
    @SerializedName("selected")
    var isSelected: Boolean?
)

data class SessionForUser(
    @SerializedName("_id")
    val id: String?,
    @SerializedName("_session_id")
    val sessionId: String?,
    @SerializedName("delivery_symbol")
    val deliverySymbol: String?,
    @SerializedName("session_type")
    val sessionType: String?,
    @SerializedName("session_topic")
    val sessionTopic: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("s_no")
    val sNo: Int?,
    @SerializedName("delivery_no")
    val deliveryNo: Int?,
)
