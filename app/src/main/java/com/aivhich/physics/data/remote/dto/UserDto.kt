package com.aivhich.physics.data.remote.dto

import android.util.Log
import com.aivhich.physics.domain.model.User
import com.google.gson.annotations.SerializedName
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

data class UserDto(
    @SerializedName("id")
    var id: UUID,
    @SerializedName("name")
    val name:String,
    @SerializedName("surname")
    val surname:String,
    @SerializedName("code")
    val code:String?,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("emailVerified")
    val isEmailVerified: Boolean,
    @SerializedName("role")
    val role: Role
)
enum class Role{
    USER,
}
////error here sync classes
fun UserDto.toUser(): User {
    return User(
        id = id,
        name = name,
        surname = surname,
        nickname = nickname,
        code = code,
        email = email,
        isEmailVerified = isEmailVerified,
        timestamp = ZonedDateTime.now(ZoneId.of("UTC-0")).toEpochSecond()
    )
}