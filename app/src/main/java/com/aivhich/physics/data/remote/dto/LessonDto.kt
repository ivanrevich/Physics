package com.aivhich.physics.data.remote.dto

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class LessonDto(
    @SerializedName("id")
    val id: UUID,
    @SerializedName("infoType")
    val infoType:InfoType,
    @SerializedName("stepId")
    val stepId: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("pages")
    var pages: List<PageDto>

)