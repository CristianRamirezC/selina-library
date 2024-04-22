package com.dashfleet.selinaLibrary.data.network

import com.dashfleet.selinaLibrary.data.model.login.LoginRequestBodyModel
import com.dashfleet.selinaLibrary.data.model.login.LoginResponseModel
import com.google.gson.Gson

class WebServiceRepository {

    private val httpRequest: HttpRequestTest = HttpRequestTest()
    private val gson = Gson()

    fun loginSAE(loginRequestBody: LoginRequestBodyModel): LoginResponseModel {
        try {
            val endpoint = "sanctum/token"
            val response = httpRequest.postJSON(
                endpoint = endpoint,
                body = loginRequestBody
            )

        return gson.fromJson(response, LoginResponseModel::class.java)
//            return response
        } catch (e: Exception) {
            return LoginResponseModel()
        }
    }
}