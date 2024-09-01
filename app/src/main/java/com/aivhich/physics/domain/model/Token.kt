package com.aivhich.physics.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Token(
    @PrimaryKey val id:Int?,
    val accesssToken:String,
    val refreshToken:String
)