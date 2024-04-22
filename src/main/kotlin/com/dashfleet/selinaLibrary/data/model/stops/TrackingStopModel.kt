package com.dashfleet.selinaLibrary.data.model.stops

import com.google.gson.annotations.SerializedName

data class TrackingStopModel(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("pso_stop_id") val stopId: Int = 0,
    @SerializedName("lat") var latitude: String = "",
    @SerializedName("lng") var longitude: String = "",
    @SerializedName("name") var name: String = "",
    @SerializedName("order") var order: String = "",
    @SerializedName("radio") var radio: String = "",
    @SerializedName("status") var status: Int = 0,
    @SerializedName("time") var time: String = "",
    @SerializedName("order_stop") var orderStop: Int = 0,
    @SerializedName("bearing") var bearing: Float = 0f,
    //New stop items for mode A
    @SerializedName("calculated_bearing") val calculatedBearing: Double = 0.0,
    @SerializedName("subpaths") var subpaths: List<Subpaths> = listOf(Subpaths()),
    val distance: Int = 0,
    var isStop: Boolean = false,
    var subpathId: Int = 0
)

data class Subpaths(
    @SerializedName("distance") val distance: Int = 0,
    @SerializedName("lat_subpath_i") val latitude: Double = 0.0,
    @SerializedName("lng_subpath_i") val longitude: Double = 0.0,
    @SerializedName("order_subpath") val subpathOrder: Int = 0,
    @SerializedName("radio") val radio: String = "",
    @SerializedName("stop_i_id") val stopId: Int = 0,
    @SerializedName("_id") val id: String = "",
    @SerializedName("calculated_bearing") val calculatedBearing: Double = 0.0,
    @SerializedName("id") val subpathId: Int = 0
)