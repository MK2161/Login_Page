package com.example.login.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DocumentUserDetail(
    @SerializedName("_id")
    var staffId: String?,

    @SerializedName("name")
    var name: NameModel?,
)
class NameModel {

    @SerializedName("first")
    @Expose
    @ColumnInfo
    var first: String? = null

    @SerializedName("last")
    @Expose
    @ColumnInfo
    var last: String? = null

    @SerializedName("middle")
    @Expose
    @ColumnInfo
    var middle: String? = null

    @SerializedName("family")
    @Expose
    @ColumnInfo
    var family: String? = null
}