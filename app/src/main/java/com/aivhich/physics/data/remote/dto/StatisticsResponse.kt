package com.aivhich.physics.data.remote.dto

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class StatisticsResponse(
    @SerializedName("courseProgress")
    val courseProgress:Int,

    @SerializedName("points")
    val points:Long,

    @SerializedName("answerTasks")
    val answerTasks:Long
)
