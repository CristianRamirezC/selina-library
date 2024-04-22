package com.dashfleet.selinaLibrary.data.model.services

import com.google.gson.annotations.SerializedName

data class GetServicesRequestBodyModel(
    @SerializedName("employee_id") val employeeId: Int?,
    @SerializedName("pso_routes") val psoRoutes: List<Int>?,
    @SerializedName("detail_service_id") val detailServiceId: Int?,
    @SerializedName("status") val status: Int?,
    @SerializedName("type") val type: String?,
    @SerializedName("imei") val imei: String?
)
