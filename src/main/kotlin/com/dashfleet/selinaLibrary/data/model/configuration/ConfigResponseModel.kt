package com.dashfleet.selinaLibrary.data.model.configuration

import com.google.gson.annotations.SerializedName

data class ConfigResponseModel(
    @SerializedName("host") val host: String? = null,
    @SerializedName("time") val time: Time? =Time(),
    @SerializedName("mode") val mode: String? = null,
    @SerializedName("action") val action: Action? = Action(),
    @SerializedName("token") val token: String? = null,
    @SerializedName("hash") val hash: String? = null
)

class Time (
    @SerializedName("frecuency") val frequency: Int? = null,
    @SerializedName("notification") val notification: Int? = null,
    @SerializedName("service") val service: Int? = null,
    @SerializedName("send") val send: Int? = null,
    @SerializedName("jumbo") val jumbo: Int? = null,
)

class Action (
    @SerializedName("export_pending") val exportPending: Int? = null,
    @SerializedName("export_all") val exportAll: Int? = null,
    @SerializedName("export_copy") val exportCopy: Int? = null
)
