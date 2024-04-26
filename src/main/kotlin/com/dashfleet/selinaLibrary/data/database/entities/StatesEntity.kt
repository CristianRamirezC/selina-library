package com.dashfleet.selinaLibrary.data.database.entities

import lombok.*
import javax.persistence.*

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name="states")
class StatesEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id: Int = 0,
    @Column(name = "gps_state") val gpsState: Boolean = false,
    @Column(name = "gps_latitude") val gpsLatitude: Double = 0.0,
    @Column(name = "gps_longitude") val gpsLongitude: Double = 0.0,
    @Column(name = "gps_speed") val gpsSpeed: Double = 0.0,
    @Column(name = "gps_course") val gpsCourse: Double = 0.0,
    @Column(name = "gps_satellites") val gpsSatellites: Int = 0,
    @Column(name = "gps_datetime") val gpsDatetime: String = "",
    @Column(name = "io_door_one") val doorOneStatus: String = "",
    @Column(name = "io_door_two") val doorTwoStatus: String = "",
    @Column(name = "io_door_three") val doorThreeStatus: String = "",
    @Column(name = "io_ignition") val ignitionStatus: String = "",
    @Column(name = "io_panic") val panicStatus: String = "",
    @Column(name = "modem_network_type") val modemNetworkType: String = "",
    @Column(name = "modem_signal_level") val modemSignalLevel: Int = 0,
    @Column(name = "modem_snr_level") val modemSNRLevel: Int = 0,
    @Column(name = "modem_state") val modemState: Boolean = false,
    @Column(name = "display_state") val displayState: Boolean = false,
    @Column(name = "turnstile_state") val turnstileState: Boolean = false,
    @Column(name = "validator_state") val validatorState: Boolean = false,
    @Column(name = "apc_state") val apcState: Boolean = false,
    @Column(name = "apc_in") val apcInCounter: Long = 0L,
    @Column(name = "apc_out") val apcOutCounter: Long = 0L,
    @Column(name = "odo_count") val odoCounter: Float = 0f,
    @Column(name = "gui_route_state") val guiRouteState: Int = 0,
    @Column(name = "gui_route_id") val guiRouteId: Int = 0,
    @Column(name = "gui_route_id_service") val guiRouteIdService: Int = 0,
    @Column(name = "gui_route_eta_back") val guiRouteETABack: Int = 0,
    @Column(name = "gui_route_eta") val guiRouteETA: String = "",
    @Column(name = "gui_route_eta_front") val guiRouteETAFront: Int = 0,
    @Column(name = "gui_route_total_distance") val guiRouteTotalDistance: Double = 0.0,
    @Column(name = "gui_route_network") val guiRouteNetwork: String = "",
    @Column(name = "gui_route_total_time") val guiRouteTotalTime: String = "",
    @Column(name = "gui_route_stop_start_id") val guiRouteStopStartId: Int = 0,
    @Column(name = "gui_route_stop_end_id") val guiRouteStopEndId: Int = 0,
    @Column(name = "gui_route_path_stop_start_id") val guiRoutePathStopStartId: String = "",
    @Column(name = "gui_route_path_stop_end_id") val guiRoutePathStopEndId: String = "",
    @Column(name = "gui_route_path_stop_end_distance") val guiRoutePathStopEndDistance: Double = 0.0,
    @Column(name = "gui_route_path_stop_end_time") val guiRoutePathStopEndTime: String = "",
    @Column(name = "gui_route_next_subpath_id") val guiRouteNextSubpathId: Int = 0,
    @Column(name = "gui_route_next_subpath_distance") val guiRouteNextSubpathDistance: Double = 0.0,
    @Column(name = "gui_route_next_subpath_time") val guiRouteNextSubpathTime: String = "",
    @Column(name = "gui_route_next_stop_id") val guiRouteNextStopId: Int = 0,
    @Column(name = "gui_route_next_stop_time") val guiRouteNextStopTime: String = "",
    @Column(name = "gui_route_pso_id") val guiRoutePSOId: Int = 0,
    @Column(name = "gui_route_detail_service_id") val guiRouteDetailServiceId: Int = 0,
    @Column(name = "gui_route_current_subpath_id") val guiRouteCurrentSubpathId: Int = 0,
    @Column(name = "gui_route_advance") val guiRouteAdvance: Double = 0.0,
    @Column(name = "gui_route_state_sinoptic") val guiRouteStateSinoptic: Int = 0,
    @Column(name = "gui_route_information") val guiRouteInformation: String = "",
    @Column(name = "gui_route_detail_service_stops") val guiRouteDetailServiceStops: String = "",
    @Column(name = "ses_id_user") val sesIdUser: String = "",
    @Column(name = "ses_user") val sesUser: String = "",
    @Column(name = "ses_token") val sesToken: String = "",
)