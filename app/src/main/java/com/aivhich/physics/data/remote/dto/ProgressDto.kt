package com.aivhich.physics.data.remote.dto

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class ProgressDto(
    @SerializedName("lesson")
    val lesson:UUID,
    @SerializedName("points")
    val points:Long
)