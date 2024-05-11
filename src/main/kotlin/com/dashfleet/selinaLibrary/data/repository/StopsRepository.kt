package com.dashfleet.selinaLibrary.data.repository

import com.dashfleet.selinaLibrary.data.model.stops.GetUpdatedStopsResponse
import com.dashfleet.selinaLibrary.data.network.HttpRequest
import com.google.gson.Gson

class StopsRepository {

    private val httpRequest: HttpRequest = HttpRequest()
    private val gson = Gson()

    fun getUpdatedStopsSAE(serviceID: Int): GetUpdatedStopsResponse {
        try {
            val endpoint = "v1/selina-service-stops/$serviceID"
            val response = httpRequest.getJSON(
                endpoint = endpoint
            )

            return gson.fromJson(response, GetUpdatedStopsResponse::class.java)
        } catch (e: Exception) {
            //TODO
            return GetUpdatedStopsResponse()
        }
    }

}