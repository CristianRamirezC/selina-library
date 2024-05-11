package com.dashfleet.selinaLibrary.data.repository

import com.dashfleet.selinaLibrary.data.model.user.ValidateTechnicalUserRequestBodyModel
import com.dashfleet.selinaLibrary.data.model.user.ValidateTechnicalUserResponseModel
import com.dashfleet.selinaLibrary.data.network.HttpRequest
import com.google.gson.Gson

class UserRepository {
    private val httpRequest: HttpRequest = HttpRequest()
    private val gson = Gson()

    fun validateUser() {
        try {
            val endpoint = "user"
            val response = httpRequest.getJSON(
                endpoint = endpoint
            )
        } catch (e: Exception) {
            //TODO
        }
    }

    fun validateTechnicalUser(
        validateTechnicalUserRequestBodyModel: ValidateTechnicalUserRequestBodyModel
    ): ValidateTechnicalUserResponseModel {
        try {
            val endpoint = "v1/validate-pin-technical-user"
            val response = httpRequest.postJSON(
                endpoint = endpoint,
                body = validateTechnicalUserRequestBodyModel
            )

            return gson.fromJson(response, ValidateTechnicalUserResponseModel::class.java)
        } catch (e: Exception) {
            //TODO
            return ValidateTechnicalUserResponseModel()
        }
    }


}