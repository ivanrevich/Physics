package com.aivhich.physics.data.remote.dto

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class FormDto(
    @SerializedName("id")
    val id: UUID,
    @SerializedName("formType")
    val formType: FormType,
    @SerializedName("answers")
    val answers: Collection<String>,
    @SerializedName("rightAnswers")
    val rightAnswers: Collection<String>
)
