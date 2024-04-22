package com.dashfleet.selinaLibrary.data.model.route

import com.google.gson.annotations.SerializedName

data class GetRouteResponseModel(
    @SerializedName("data") val data: PSONetworks = PSONetworks()
)

class PSONetworks(
    @SerializedName("pso_networks") val psoNetworkList: List<PSORouteList> = listOf()
)

class PSORouteList(
    @SerializedName("pso_routes") val psoRoutesList: List<PSORoute> = listOf(),
    @SerializedName("status") val status: Int? = null
)

class PSORoute(
    @SerializedName("id_route") val routeId: String? = null,
    @SerializedName("name_short") val nameShort: String? = null
)
