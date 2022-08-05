package com.example.login.model

import com.google.gson.annotations.SerializedName

data class NotificationResponse(
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
    val data: ArrayList<NotificationItem>?,
)

data class NotificationItem(
    @SerializedName("isDeleted")
    val isDeleted: Boolean?,

    @SerializedName("isActive")
    val isActive: Boolean?,

    @SerializedName("read")
    val isRead: Boolean?,

    @SerializedName("_id")
    val notificationId: String?,

    @SerializedName("timeAgo")
    val timeAgo: String?,

    @SerializedName("notificationType")
    val notificationType: String?,

    @SerializedName("notification_priority")
    val notificationPriority: String?,

    @SerializedName("_user_id")
    val userId: String?,

    @SerializedName("notification_to")
    val notificationTo: String?,

    @SerializedName("timeToLive")
    val timeToLive: Int?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("data")
    val data: String?,

    @SerializedName("status")
    val status: String?,

    @SerializedName("createdAt")
    val createdAt: String?,

    @SerializedName("notificationPeriod")
    val notificationPeriod: String?,

    @SerializedName("updatedAt")
    val updatedAt: String?,

    @SerializedName("courseId")
    val courseId: String? = null,

    @SerializedName("sessionId")
    val sessionId: String? = null,

    @SerializedName("sessionType")
    val sessionType: String? = null,

    @SerializedName("institutionCalendarId")
    val institutionCalendarId: String? = null,

    @SerializedName("programId")
    val programId: String? = null,

    @SerializedName("yearNo")
    val yearNo: String? = null,

    @SerializedName("mode")
    val mode: String? = null,

    @SerializedName("scheduleId")
    val scheduleId: String? = null,

    @SerializedName("levelNo")
    val levelNo: String? = null,

    @SerializedName("type")
    var type: String? = null,

    @SerializedName("mergeStatus")
    val mergeStatus: Boolean? = null,

    @SerializedName("rotation")
    val rotation: String? = null,

    @SerializedName("term")
    val term: String? = null,

    @SerializedName("rotation_count")
    val rotationCount: Int? = null,

    @SerializedName("activityId")
    val activityId: String?,

    var isSelected: Boolean? = false,


    @SerializedName("quizType")
    var quizType: String? = null,

    @SerializedName("createdBy")
    val createdBy: String? = null,

    @SerializedName("socketEventStaffId")
    var socketEventStaffId: String? = null,

    @SerializedName("staffStartWithExam")
    var staffStartWithExam: String? = null,

    @SerializedName("socketEventStudentId")
    var socketEventStudentId: String? = null,

    @SerializedName("totalStudentAnsweredCount")
    var totalStudentAnsweredCount: String? = null,

    @SerializedName("totalStudentCount")
    var totalStudentCount: String? = null,

    @SerializedName("name")
    var activityName: String? = null,

    @SerializedName("studentGroupName")
    var studentGroupName: String? = null,
    @SerializedName("course_name")
    var courseName: String? = null,
   /* @SerializedName("schedule")
    var schedule: ScheduleRequestData? = null,
*/
    @SerializedName("setQuizTime")
    var setQuizTime: Long? = null,

    )
data class ScheduleRequestData(
    @SerializedName("startDateAndTime")
    var startDateAndTime: String?,

    @SerializedName("endDateAndTime")
    var endDateAndTime: String?,
)

