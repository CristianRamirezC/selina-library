package com.dashfleet.selinaLibrary.data.model.user

import com.google.gson.annotations.SerializedName

data class ValidateTechnicalUserRequestBodyModel(
    @SerializedName("pin") val pin: String? = null
)
