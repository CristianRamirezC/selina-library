package com.dashfleet.selinaLibrary.data.model.rates

import com.google.gson.annotations.SerializedName

data class GetRatesResponseModel(
    @SerializedName("error") var error: String? = null,
    @SerializedName("code") var code: Int? = null,
    @SerializedName("data") var data: ArrayList<RateEntity> = arrayListOf()
)

data class RateEntity(
    @SerializedName("id") var id: Int,
    @SerializedName("rate_profile") var rateProfile: String,
    @SerializedName("name_management_rate") var nameManagementRate: String,
    @SerializedName("price") var price: String
)
