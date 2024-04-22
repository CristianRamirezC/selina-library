package com.dashfleet.selinaLibrary.data.model.route

import com.google.gson.annotations.SerializedName

data class RouteInformationRequestModel(
    @SerializedName("detail_services_id") val detailServicesId: Int,
    @SerializedName("imei") val imei: String
)
