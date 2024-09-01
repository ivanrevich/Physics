package com.aivhich.physics.data.remote.dto

import com.google.gson.annotations.SerializedName
import java.util.UUID


data class LevelDto(
    @SerializedName("id")
    val id:UUID,

    @SerializedName("stepId")
    val stepId:Long,

    @SerializedName("title")
    val title:String,

    @SerializedName("lessons")
    val lessons:List<LessonDto>,

    @SerializedName("course")
    val course:Course
)