package com.aivhich.physics.data.remote.dto

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class LeaderUsersResponse(

    @SerializedName("users")
    val users:List<UserDto>,

    @SerializedName("points")
    val points:Long,

    @SerializedName("place")
    val place:Int,

    @SerializedName("userPoints")
    val userPoints: Map<UUID, Long>
)
