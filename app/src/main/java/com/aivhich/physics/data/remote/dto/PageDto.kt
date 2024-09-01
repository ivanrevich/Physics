package com.aivhich.physics.data.remote.dto

import java.util.UUID

data class PageDto(
    val id:UUID,
    val infoType:InfoType,
    val texts:String,
    val points:Int,
    val stepId:Long,
    val form: FormDto
)
