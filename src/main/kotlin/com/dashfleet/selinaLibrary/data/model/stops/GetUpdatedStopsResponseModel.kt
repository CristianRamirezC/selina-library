package com.dashfleet.selinaLibrary.data.model.stops

import com.dashfleet.selinaLibrary.data.model.services.TrackingStop
import com.google.gson.annotations.SerializedName

data class GetUpdatedStopsResponse(
    @SerializedName("data") val data: DataModel = DataModel()
)

data class DataModel(
    @SerializedName("stops") val stops: String? = null,
    @SerializedName("stopsArray") val stopsArray: List<TrackingStop> = listOf()
)