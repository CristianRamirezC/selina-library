package com.dashfleet.selinaLibrary.data.model.login

import com.google.gson.annotations.SerializedName

data class LoginRequestBodyModel(
    @SerializedName("imei") val imei: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("device_name") val deviceName: String
)
