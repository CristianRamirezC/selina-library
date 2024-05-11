package com.dashfleet.selinaLibrary.data.repository

import com.dashfleet.selinaLibrary.data.model.closeSummary.CloseSummaryRequestBodyModel
import com.dashfleet.selinaLibrary.data.model.closeSummary.CloseSummaryResponseModel
import com.dashfleet.selinaLibrary.data.network.HttpRequest
import com.google.gson.Gson

class CloseRepository {

    private val httpRequest: HttpRequest = HttpRequest()
    private val gson = Gson()

    fun getCloseSummary(closeSummaryRequestBodyModel: CloseSummaryRequestBodyModel): CloseSummaryResponseModel {
        try {
            val endpoint = "v1/cash-register-selina"
            val response = httpRequest.postJSON(
                body = closeSummaryRequestBodyModel,
                endpoint = endpoint
            )

            return gson.fromJson(response, CloseSummaryResponseModel::class.java)
        } catch (e: Exception) {
            //TODO
            return CloseSummaryResponseModel()
        }
    }
}