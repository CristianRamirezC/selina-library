package com.dashfleet.selinaLibrary.data.model.login

import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @SerializedName("data") var data: Data? = Data()
)

data class User(
    @SerializedName("employee_id") var employeeId: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("id_card") var idCard: String? = null,
    @SerializedName("permission") var permission: String? = null,
    @SerializedName("vehicle") var vehicle: String? = null,
    @SerializedName("role") var role: Int? = null,
    @SerializedName("costOfTickets") var costOfTickets: ArrayList<String> = arrayListOf(),
    @SerializedName("imei") var imei: String? = null,
    @SerializedName("internal_num") var internalNum: String? = null
)

data class Data(
    @SerializedName("type") var type: String? = null,
    @SerializedName("token") var token: String? = null,
    @SerializedName("expires_in") var expiresIn: Int? = null,
    @SerializedName("user") var user: User? = User(),
    @SerializedName("redirect_uri") var redirectUri: String? = null
)