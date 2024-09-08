package com.aivhich.physics.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CourseDto(
    @SerializedName("passed")
    val passed:List<LessonDto>,
    @SerializedName("LastPassedId")
    val LastPassedId:Long
)