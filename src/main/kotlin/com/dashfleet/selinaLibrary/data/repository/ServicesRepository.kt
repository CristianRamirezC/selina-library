package com.dashfleet.selinaLibrary.data.repository

import com.dashfleet.selinaLibrary.data.model.services.GetServicesRequestBodyModel
import com.dashfleet.selinaLibrary.data.model.services.GetServicesResponseModel
import com.dashfleet.selinaLibrary.data.network.HttpRequest
import com.google.gson.Gson

class ServicesRepository {

    private val httpRequest: HttpRequest = HttpRequest()
    private val gson = Gson()

    fun getServicesSAE(getServicesRequestBody: GetServicesRequestBodyModel): GetServicesResponseModel {
        try {
            val endpoint = "v1/services"
            val response = httpRequest.postJSON(
                endpoint = endpoint,
                body = getServicesRequestBody
            )

            return gson.fromJson(response, GetServicesResponseModel::class.java)
        } catch (e: Exception) {
            return GetServicesResponseModel()
        }
    }
}