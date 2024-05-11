package com.dashfleet.selinaLibrary.data.repository

import com.dashfleet.selinaLibrary.data.model.route.GetRouteRequestBodyModel
import com.dashfleet.selinaLibrary.data.model.route.GetRouteResponseModel
import com.dashfleet.selinaLibrary.data.model.route.RouteInformationRequestModel
import com.dashfleet.selinaLibrary.data.model.route.RouteInformationResponseModel
import com.dashfleet.selinaLibrary.data.network.HttpRequest
import com.google.gson.Gson

class RoutesRepository {

    private val httpRequest: HttpRequest = HttpRequest()
    private val gson = Gson()

    fun getCurrentRouteInformationSAE(routeInformationBody: RouteInformationRequestModel): RouteInformationResponseModel {
        try {
            val endpoint = "v1/frame-selina/bus-route-to-selina"
            val response = httpRequest.postJSON(
                endpoint = endpoint,
                body = routeInformationBody
            )

            return gson.fromJson(response, RouteInformationResponseModel::class.java)
        } catch (e: Exception) {
            //TODO
            return RouteInformationResponseModel()
        }
    }

    fun getRoutesSAE(getRoutesRequestModel: GetRouteRequestBodyModel): GetRouteResponseModel {
        try {
            val endpoint = "v1/pso-network"
            val response = httpRequest.postJSON(
                endpoint = endpoint,
                body = getRoutesRequestModel
            )

            return gson.fromJson(response, GetRouteResponseModel::class.java)
        } catch (e: Exception) {
            //TODO
            return GetRouteResponseModel()
        }
    }

}