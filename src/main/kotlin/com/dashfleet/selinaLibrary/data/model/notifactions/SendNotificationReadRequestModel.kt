package com.dashfleet.selinaLibrary.data.model.notifactions

import com.google.gson.annotations.SerializedName

data class SendNotificationReadRequestModel(
    @SerializedName("status_receiver") val statusReceiver: Int
)
