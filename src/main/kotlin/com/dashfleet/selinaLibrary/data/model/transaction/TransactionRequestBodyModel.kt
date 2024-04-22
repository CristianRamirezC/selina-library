package com.dashfleet.selinaLibrary.data.model.transaction

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TransactionRequestBodyModel(
    @SerializedName("id") private var id: Int,
    @SerializedName("vehicle_id") var veh: String,
    @SerializedName("type_transaction") var type: String,
    @SerializedName("data") var data: Frame,
    @SerializedName("datetime_success") var dataCreate: String
)

data class Frame(
    val ver: String,
    val ime: String,
    val typ: String,
    val dat: String,
    val veh: String,
    val lat: String,
    val lon: String,
    val spe: String,
    val cur: String,
    val sat: String,
    val odo: String,
    val hw: String,
    val drv: String,
    val hubid: String,
    val cou: Counters,
    val route: Any? = null,
    val rec: Sale? = null,
    val clo: FrameClose? = null,
    val zcameras: List<Camera>
)

data class Counters(
    @SerializedName("in") val cin: String,
    @SerializedName("out") val cout: String,
    @SerializedName("tur") val tur: String,
    @SerializedName("oth") val oth: String,
    @SerializedName("enc") val enc: String,
    @SerializedName("valid") val valid: Boolean
)

data class Sale(
    @SerializedName("typ") @Expose val typ: String,
    @SerializedName("qty") val qty: String,
    @SerializedName("tot") val tot: String
)

data class FrameClose(
    @SerializedName("olderPeople") val older_people: String,
    @SerializedName("students") val students: String,
    @SerializedName("regularPeople") val regular_people: String,
    @SerializedName("collectionValue") val collection_value: String,
    @SerializedName("driverCode") val driver_code: String,
    @SerializedName("masterCard") val master_card: String
)

data class Camera(
    val id: Int = 0,
    val countIn: Int = 0,
    val countOut: Int = 0
)
