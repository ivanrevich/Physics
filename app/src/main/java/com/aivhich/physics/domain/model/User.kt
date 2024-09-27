package com.aivhich.physics.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class User(
    @PrimaryKey
    val id: UUID,
    val name:String,
    val surname:String,
    val nickname:String,
    val code:String?,
    val email:String,
    val isEmailVerified:Boolean,
    val timestamp: Long,
)