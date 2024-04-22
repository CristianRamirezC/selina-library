package com.dashfleet.selinaLibrary.data.model.notifactions

import com.google.gson.annotations.SerializedName

data class NotificationResponseModel(
    @SerializedName("notifications") var notifications: ArrayList<SelinaNotification>? = null,
    @SerializedName("messages") var messages: ArrayList<SelinaMessage>? = null
)

data class SelinaNotification(
    @SerializedName("id") var id: String? = null,
    @SerializedName("delay") var delay: String? = null,
    @SerializedName("destination") var destination: String? = null,
    @SerializedName("notification_generate_code") var code: String? = null,
    @SerializedName("notification_generate_name") var name: String? = null,
    @SerializedName("personalized_notification") var personalizedNotification: String? = null,
)

data class SelinaMessage(
    @SerializedName("id") var id: String? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("subject") var subject: String? = null,
    @SerializedName("vehicle_internal_num") var user: String? = null,
    @SerializedName("created_at") var createdDate: String? = null
)
