package com.dashfleet.selinaLibrary.data.model.message

import com.google.gson.annotations.SerializedName

data class SendMessageReadRequestModel(
    @SerializedName("status_receiver") val statusReceiver: Int
)