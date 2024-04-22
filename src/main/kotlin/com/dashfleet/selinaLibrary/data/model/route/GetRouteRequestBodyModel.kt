package com.dashfleet.selinaLibrary.data.model.route

import com.google.gson.annotations.SerializedName

data class GetRouteRequestBodyModel(
    @SerializedName("pso_mangement_id") val psoManagementId: Int? = null,
    @SerializedName("employee_id") val employeeId: Int? = null,
    @SerializedName("pso_routes") val psoRoutes: List<Int>? = null
)
