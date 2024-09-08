package com.aivhich.physics.data.remote.dto

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class UpdateProgress(
    @SerializedName("rightForms")
    var rightForms:List<UUID>,

    @SerializedName("passedPages")
    var passedPages:List<UUID>
)