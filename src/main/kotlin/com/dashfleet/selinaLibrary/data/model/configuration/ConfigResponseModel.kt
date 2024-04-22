package com.dashfleet.selinaLibrary.data.model.configuration

import com.google.gson.annotations.SerializedName

data class ConfigResponseModel(
    @SerializedName("host") val host: String,
    @SerializedName("time") val time: Time,
    @SerializedName("mode") val mode: String? = null,
    @SerializedName("action") val action: Action,
    @SerializedName("token") val token: String,
    @SerializedName("hash") val hash: String
)

class Time (
    @SerializedName("frecuency") val frecuency: Int,
    @SerializedName("notification") val notification: Int,
    @SerializedName("service") val service: Int,
    @SerializedName("send") val send: Int,
    @SerializedName("jumbo") val jumbo: Int,
)

class Action (
    @SerializedName("export_pending") val exportPending: Int,
    @SerializedName("export_all") val exportAll: Int,
    @SerializedName("export_copy") val exportCopy: Int
)
