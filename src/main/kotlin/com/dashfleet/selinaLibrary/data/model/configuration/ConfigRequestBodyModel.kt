package com.dashfleet.selinaLibrary.data.model.configuration

import com.google.gson.annotations.SerializedName

data class ConfigRequestBodyModel(
    @SerializedName("imei") val imei: String
)
