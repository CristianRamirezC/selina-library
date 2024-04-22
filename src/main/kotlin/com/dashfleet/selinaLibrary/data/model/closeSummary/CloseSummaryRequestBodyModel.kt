package com.dashfleet.selinaLibrary.data.model.closeSummary

import com.google.gson.annotations.SerializedName

data class CloseSummaryRequestBodyModel(
    @SerializedName("from") val initialDate: String? = null,
    @SerializedName("to") val finalDate: String? = null,
    @SerializedName("vehicle_id") val vehicleId: String? = null,
    @SerializedName("employee_id") val employeeId: String? = null
)
