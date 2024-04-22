package com.dashfleet.selinaLibrary.data.model.route

import com.google.gson.annotations.SerializedName

data class RouteInformationResponseModel(
    @SerializedName("name_driver") var driverName: String? = null,
    @SerializedName("route_name") var routeName: String? = null,
    @SerializedName("initial_stop") var initialStop: String? = null,
    @SerializedName("initial_time") var initialTime: String? = null,
    @SerializedName("final_stop") var finalStop: String? = null,
    @SerializedName("final_time") var finalTime: String? = null,
    @SerializedName("name_next_stop") var nextStopName: String? = null,
    @SerializedName("time_next_stop") var nextStopTime: String? = null,
    @SerializedName("speed") var speed: String? = null,
    @SerializedName("distance_next_stop") var distance: String? = null,
    @SerializedName("end_route") var endRoute: Boolean? = null,
    @SerializedName("eta_before") var etaBefore: String? = null,
    @SerializedName("eta") var eta: String? = null,
    @SerializedName("eta_after") var etaAfter: String? = null
)
