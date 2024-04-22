package com.dashfleet.selinaLibrary.data.model.services

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

data class GetServicesResponseModel(
    @SerializedName("data") var data: DataService = DataService()
)

data class DataService(
    @SerializedName("pso_routes") var psoRoutes: ArrayList<PsoRoutes> = arrayListOf()
)

data class PsoRoutes(
    @SerializedName("id_route") var idRoute: Int = 0,
    @SerializedName("total_stops") var totalStops: Int = 0,
    @SerializedName("total_distance") var totalDistance: Int = 0,
    @SerializedName("name_short") var nameShort: String = "",
    @SerializedName("name_long") var nameLong: String = "",
    @SerializedName("stops") var stops: ArrayList<Stops> = arrayListOf(),
    @SerializedName("services") var services: ArrayList<Services> = arrayListOf()
)

data class Stops(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("short_name") var shortName: String = "",
    @SerializedName("long_name") var longName: String = "",
    @SerializedName("arrival_time") var arrivalTime: String = "",
    @SerializedName("lat") var lat: String = "",
    @SerializedName("lng") var lng: String = "",
    @SerializedName("radio") var radio: String = "",
    @SerializedName("stop_type") var stopType: Int = 0,
    @SerializedName("subpaths") var subpaths: ArrayList<SubpathService> = arrayListOf()
)

data class SubpathService(
    @SerializedName("distance") var distance: Int = 0,
    @SerializedName("time") var time: String = "",
    @SerializedName("id") var id: Int = 0,
    @SerializedName("radio") var radio: String = "",
    @SerializedName("lat_subpath_i") var latSubpathI: Double = 0.0,
    @SerializedName("lng_subpath_i") var lngSubpathI: Double = 0.0,
    @SerializedName("lat_subpath_f") var latSubpathF: Double = 0.0,
    @SerializedName("lng_subpath_f") var lngSubpathF: Double = 0.0,
    )

data class Services(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("pi") var pi: Int = 0,
    @SerializedName("hi") var hi: String = "",
    @SerializedName("pf") var pf: Int = 0,
    @SerializedName("hf") var hf: String = "",
    @SerializedName("dis") var dis: Int = 0,
    @SerializedName("detail_services") var detailServices: ArrayList<DetailServices> = arrayListOf()
)

data class DetailServices(
    @SerializedName("start_time") var startTime: String = "",
    @SerializedName("end_time") var endTime: String = "",
    @SerializedName("id") var id: Int = 0,
    @SerializedName("ser_bus") var serBus: String = "",
    @SerializedName("ser_con") var serCon: String = "",
    @SerializedName("frequency") var frequency: String = "",
    @SerializedName("status") var status: Int = 0,
    @SerializedName("stops") var stops: String = "",
    @SerializedName("stopsArray") var stopsArray: ArrayList<TrackingStop> = arrayListOf(),
    @SerializedName("service_pso_route_id") var servicePsoRouteId: Int = 0,
    @SerializedName("employee_id") var employeeId: Int = 0,
    @SerializedName("employee_full_name") var employeeFullName: String = "",
    @SerializedName("employee_application") var employeeApplication: String = "",
    @SerializedName("employee_telephone") var employeeTelephone: String = "",
    @SerializedName("employee_address") var employeeAddress: String = "",
    @SerializedName("employee_email") var employeeEmail: String = "",
    @SerializedName("employee_assign_vehicle") var employeeAssignVehicle: String = "",
    @SerializedName("employee_vehicle_type") var employeeVehicleType: String = "",
    @SerializedName("employee_contract_date") var employeeContractDate: String = "",
    @SerializedName("employee_pass_number") var employeePassNumber: String = "",
    @SerializedName("employee_license_category") var employeeLicenseCategory: String = "",
    @SerializedName("employee_license_expiration_date") var employeeLicenseExpirationDate: String = "",
    @SerializedName("employee_master_card") var employeeMasterCard: String = "",
    @SerializedName("employee_status") var employeeStatus: Int = 0,
    @SerializedName("vehicle_id") var vehicleId: Int = 0,
    @SerializedName("vehicle_class") var vehicleClass: String = "",
    @SerializedName("vehicle_plate") var vehiclePlate: String = "",
    @SerializedName("vehicle_type") var vehicleType: String = "",
    @SerializedName("vehicle_ability") var vehicleAbility: String = "",
    @SerializedName("vehicle_phone_num") var vehiclePhoneNum: Long = 0,
    @SerializedName("vehicle_line") var vehicleLine: String = "",
    @SerializedName("vehicle_internal_num") var vehicleInternalNum: String = "",
    @SerializedName("vehicle_gas_type") var vehicleGasType: String = "",
    @SerializedName("vehicle_brand") var vehicleBrand: String = "",
    @SerializedName("vehicle_model") var vehicleModel: String = "",
    @SerializedName("vehicle_chasis_num") var vehicleChasisNum: String = "",
    @SerializedName("vehicle_motor_num") var vehicleMotorNum: String = "",
    @SerializedName("vehicle_operation_card") var vehicleOperationCard: String = "",
    @SerializedName("vehicle_expired_ope_card") var vehicleExpiredOpeCard: String = "",
    @SerializedName("vehicle_insurence") var vehicleInsurence: String = "",
    @SerializedName("vehicle_soat") var vehicleSoat: String = "",
    @SerializedName("vehicle_expired_soat") var vehicleExpiredSoat: String = "",
    @SerializedName("vehicle_expired_tecnomeca") var vehicleExpiredTecnomeca: String = "",
    @SerializedName("vehicle_tecnomeca") var vehicleTecnomeca: String = "",
    @SerializedName("vehicle_trans_card") var vehicleTransCard: String = "",
    @SerializedName("vehicle_expired_trans_card") var vehicleExpiredTransCard: String = "",
    @SerializedName("vehicle_mileage") var vehicleMileage: Int = 0,
    @SerializedName("vehicle_odometer") var vehicleOdometer: Int = 0,
    @SerializedName("vehicle_operator") var vehicleOperator: String = "",
    @SerializedName("vehicle_imei") var vehicleImei: String = "",
    @SerializedName("synoptic") var synoptic: ArrayList<Synoptic> = arrayListOf()
)

data class Synoptic(
    @SerializedName("vehicle_id") var vehicleId: Int = 0,
    @SerializedName("real_bus_distance") var realBusDistance: Int = 0,
    @SerializedName("ser_bus_distance") var serBusDistance: Int = 0,
    @SerializedName("name") var name: String = "",
    @SerializedName("ser_bus_name") var serBusName: String = "",
    @SerializedName("eta") var eta: Double = 0.0,
)

data class TrackingStop(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("pso_stop_id") val stopId: Int = 0,
    @SerializedName("lat") var latitude: String = "",
    @SerializedName("lng") var longitude: String = "",
    @SerializedName("name") var name: String = "",
    @SerializedName("order") var order: String = "",
    @SerializedName("radio") var radio: String = "",
    @SerializedName("status") var status: Int = 0,
    @SerializedName("time") var time: String = "",
    @SerializedName("order_stop") var orderStop: Int = 0,
    @SerializedName("bearing") var bearing: Float = 0f,
    //New stop items for mode A
    @SerializedName("calculated_bearing") val calculatedBearing: Double = 0.0,
    @SerializedName("subpaths") var subpaths: List<Subpaths> = listOf(Subpaths()),
    val distance: Int = 0,
    var isStop: Boolean = false,
    var subpathId: Int = 0
)

data class Subpaths(
    @SerializedName("distance") val distance: Int = 0,
    @SerializedName("lat_subpath_i") val latitude: Double = 0.0,
    @SerializedName("lng_subpath_i") val longitude: Double = 0.0,
    @SerializedName("order_subpath") val subpathOrder: Int = 0,
    @SerializedName("radio") val radio: String = "",
    @SerializedName("stop_i_id") val stopId: Int = 0,
    @SerializedName("_id") val id: String = "",
    @SerializedName("calculated_bearing") val calculatedBearing: Double = 0.0,
    @SerializedName("id") val subpathId: Int = 0

)

data class UnifiedStops(
    val latitude: String = "",
    val longitude: String = "",
    val name: String = "",
    var orderStop: Int = 0,
    val radio: String = "",
    val calculatedBearing: Double = 0.0,
    val distance: Int = 0,
    val time: String = "",
    var isStop: Boolean = false,
    val subpathId: Int = 0,

    )

fun TrackingStop.setCurrentTime() = TrackingStop(
    bearing = bearing,
    calculatedBearing = calculatedBearing,
    id = id,
    latitude = latitude,
    longitude = longitude,
    name = name,
    order = order,
    orderStop = orderStop,
    radio = radio,
    status = status,
    time = getTimeString(),
    subpaths = listOf(Subpaths()),
    stopId = stopId
//    "${calendar.get(Calendar.HOUR_OF_DAY)}:${calendar.get(Calendar.MINUTE)}:${calendar.get(Calendar.SECOND)}"
)

private fun getTimeString(): String {
    val calendar: Calendar = Calendar.getInstance()
    val date = calendar.time

    val format = SimpleDateFormat("HH:mm:ss")
    return format.format(date)
}


