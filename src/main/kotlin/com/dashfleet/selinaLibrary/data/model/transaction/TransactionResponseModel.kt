package com.dashfleet.selinaLibrary.data.model.transaction

import com.google.gson.annotations.SerializedName

data class TransactionResponseModel(
    @SerializedName("data") val data: TransactionData = TransactionData()
)

data class TransactionData(
    @SerializedName("message") val message: String? = null,
    @SerializedName("key") val key: String? = null,
)
