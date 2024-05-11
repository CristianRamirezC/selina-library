package com.dashfleet.selinaLibrary.data.repository

import com.dashfleet.selinaLibrary.data.model.login.LoginRequestBodyModel
import com.dashfleet.selinaLibrary.data.model.login.LoginResponseModel
import com.dashfleet.selinaLibrary.data.network.HttpRequest
import com.google.gson.Gson

class LoginRepository {
    private val httpRequest: HttpRequest = HttpRequest()
    private val gson = Gson()


    //login SAE
    fun loginSAE(loginRequestBody: LoginRequestBodyModel): LoginResponseModel {
        try {
            val endpoint = "sanctum/token"
            val response = httpRequest.postJSON(
                endpoint = endpoint,
                body = loginRequestBody
            )

            return gson.fromJson(response, LoginResponseModel::class.java)
        } catch (e: Exception) {
            return LoginResponseModel()
        }
    }
}