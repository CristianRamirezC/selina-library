package com.dashfleet.selinaLibrary.data.database.dao

import com.dashfleet.selinaLibrary.data.database.DAOUtils
import com.dashfleet.selinaLibrary.data.database.HibernateSession
import com.dashfleet.selinaLibrary.data.database.entities.StatesEntity

class StatesDao {

    private val daoUtil = DAOUtils(StatesEntity::class.java)
    private val tableNameEntity = "StatesEntity"

    /** Insert a state in DD BB **/
    fun storeState(entityToStore: StatesEntity) {
        daoUtil.executeInsert(entityToStore)
    }

    fun getAllStates(): List<StatesEntity> {
        return daoUtil.executeSelectAll(tableNameEntity)
    }

    ////////////////////////////////GPS functions//////////////////////////////////
    fun updateGPSStateById(id: Int, gpsState: Boolean) {
        val columnName = "gps_state"
        daoUtil.executeUpdateById(columnName = columnName, value = gpsState, id = id)
    }

    fun updateGpsLatitudeById(id: Int, latitude: Double) {
        val columnName = "gps_latitude"
        daoUtil.executeUpdateById(columnName = columnName, value = latitude, id = id)
    }

    fun updateGpsLongitudeById(id: Int, longitude: Double) {
        val columnName = "gps_longitude"
        daoUtil.executeUpdateById(columnName = columnName, value = longitude, id = id)
    }

    fun updateGpsSpeedById(id: Int, speed: Double) {
        val columnName = "gps_speed"
        daoUtil.executeUpdateById(columnName = columnName, value = speed, id = id)
    }

    fun updateGpsCourseById(id: Int, course: Double) {
        val columnName = "gps_course"
        HibernateSession.initHibernateSession()
        daoUtil.executeUpdateById(columnName = columnName, value = course, id = id)
    }

    fun updateGpsSatellitesById(id: Int, satellites: Int) {
        val columnName = "gps_satellites"
        daoUtil.executeUpdateById(columnName = columnName, value = satellites, id = id)
    }

    fun updateGpsDatetimeById(id: Int, datetime: String) {
        val columnName = "gps_datetime"
        HibernateSession.initHibernateSession()
        daoUtil.executeUpdateById(columnName = columnName, value = datetime, id = id)
    }

    ////////////////////////////////IOS functions//////////////////////////////////

    fun updateDoorOneStatusById(id: Int, doorOneStatus: String) {
        val columnName = "io_door_one"
        daoUtil.executeUpdateById(columnName = columnName, value = doorOneStatus, id = id)
    }

    fun updateDoorTwoStatusById(id: Int, doorTwoStatus: String) {
        val columnName = "modem_signal_level"
        daoUtil.executeUpdateById(columnName = columnName, value = doorTwoStatus, id = id)
    }

    fun updateDoorTreeStatusById(id: Int, doorThreeStatus: String) {
        val columnName = "io_door_three"
        daoUtil.executeUpdateById(columnName = columnName, value = doorThreeStatus, id = id)
    }

    fun updateIgnitionStatusById(id: Int, ignitionStatus: String) {
        val columnName = "io_ignition"
        daoUtil.executeUpdateById(columnName = columnName, value = ignitionStatus, id = id)
    }

    fun updatePanicStatusById(id: Int, panicStatus: String) {
        val columnName = "io_panic"
        daoUtil.executeUpdateById(columnName = columnName, value = panicStatus, id = id)
    }

    ////////////////////////////////Modem functions//////////////////////////////////

    fun updateModemNetworkTypeById(id: Int, modemNetworkType: String) {
        val columnName = "modem_network_type"
        daoUtil.executeUpdateById(columnName = columnName, value = modemNetworkType, id = id)
    }

    fun updateModemSignalLevelById(id: Int, modemSignalLevel: Int) {
        val columnName = "modem_signal_level"
        daoUtil.executeUpdateById(columnName = columnName, value = modemSignalLevel, id = id)
    }

    fun updateModemSNRLevelById(id: Int, modemSNRLevel: Int) {
        val columnName = "modem_snr_level"
        daoUtil.executeUpdateById(columnName = columnName, value = modemSNRLevel, id = id)
    }

    fun updateModemStateById(id: Int, modemState: Boolean) {
        val columnName = "modem_state"
        daoUtil.executeUpdateById(columnName, modemState, id)
    }

    ////////////////////////////////Display functions//////////////////////////////////

    fun updateDisplayStateById(id: Int, displayState: Boolean) {
        val columnName = "display_state"
        daoUtil.executeUpdateById(columnName = columnName, value = displayState, id = id)
    }

    ///////////////////////////////Turnstile functions/////////////////////////////////

    fun updateTurnstileStateById(id: Int, turnstileState: Boolean) {
        val columnName = "turnstile_state"
        daoUtil.executeUpdateById(columnName, turnstileState, id)
    }

    /////////////////////////////////Camera functions//////////////////////////////////

    fun updateAPCStateById(id: Int, apcState: Boolean) {
        val columnName = "apc_state"
        daoUtil.executeUpdateById(columnName, apcState, id)
    }

    fun updateAPCInCounterById(id: Int, apcInCounter: Long) {
        val columnName = "apc_in"
        daoUtil.executeUpdateById(columnName = columnName, value = apcInCounter, id = id)
    }

    fun updateAPCOutCounterById(id: Int, apcOutCounter: Long) {
        val columnName = "apc_out"
        daoUtil.executeUpdateById(columnName, apcOutCounter, id)
    }

    /////////////////////////////////Odometer functions//////////////////////////////////

    fun updateOdoCounterById(id: Int, odoCounter: Float) {
        val columnName = "odo_count"
        daoUtil.executeUpdateById(columnName, odoCounter, id)
    }

    //////////////////////////////////Route functions//////////////////////////////////

    fun updateGUIRouteStateById(id: Int, guiRouteState: Int) {
        val columnName = "gui_route_state"
        daoUtil.executeUpdateById(columnName, guiRouteState, id)
    }

    fun updateGUIRouteIdById(id: Int, guiRouteId: Int) {
        val columnName = "gui_route_id"
        daoUtil.executeUpdateById(columnName, guiRouteId, id)
    }

    fun updateGUIRouteIdServiceById(id: Int, guiRouteIdService: Int) {
        val columnName = "gui_route_id_service"
        daoUtil.executeUpdateById(columnName = columnName, value = guiRouteIdService, id = id)
    }

    fun updateGUIRouteETABackById(id: Int, guiRouteETABack: Int) {
        val columnName = "gui_route_eta_back"
        daoUtil.executeUpdateById(columnName = columnName, value = guiRouteETABack, id = id)
    }

    fun updateGUIRouteETAById(id: Int, guiRouteETA: Int) {
        val columnName = "gui_route_eta"
        daoUtil.executeUpdateById(columnName = columnName, value = guiRouteETA, id = id)
    }

    fun updateGUIRouteETAFrontById(id: Int, guiRouteETAFront: Int) {
        val columnName = "gui_route_eta_front"
        daoUtil.executeUpdateById(columnName = columnName, value = guiRouteETAFront, id = id)
    }

    fun updateGUIRouteTotalDistanceById(id: Int, guiRouteTotalDistance: Double) {
        val columnName = "gui_route_eta_front"
        daoUtil.executeUpdateById(columnName = columnName, value = guiRouteTotalDistance, id = id)
    }

    fun updateGUIRouteNetworkById(id: Int, guiRouteNetwork: String) {
        val columnName = "gui_route_network"
        daoUtil.executeUpdateById(columnName = columnName, value = guiRouteNetwork, id = id)
    }

    fun updateGUIRouteTotalTimeById(id: Int, guiRouteTotalTime: String) {
        val columnName = "gui_route_total_time"
        daoUtil.executeUpdateById(columnName = columnName, value = guiRouteTotalTime, id = id)
    }

    fun updateGUIRouteStopStartIdById(id: Int, guiRouteStopStartId: Int) {
        val columnName = "gui_route_stop_start_id"
        daoUtil.executeUpdateById(columnName = columnName, value = guiRouteStopStartId, id = id)
    }

    fun updateGUIRouteStopEndIdById(id: Int, guiRouteStopEndId: Int) {
        val columnName = "gui_route_stop_end_id"
        daoUtil.executeUpdateById(columnName = columnName, value = guiRouteStopEndId, id = id)
    }

    fun updateGUIRoutePathStopStartById(id: Int, guiRoutePathStopStartId: String) {
        val columnName = "gui_route_path_stop_start_id"
        daoUtil.executeUpdateById(columnName = columnName, value = guiRoutePathStopStartId, id = id)
    }

    fun updateGUIRoutePathStopEndById(id: Int, guiRoutePathStopEndId: String) {
        val columnName = "gui_route_path_stop_end_id"
        daoUtil.executeUpdateById(columnName = columnName, value = guiRoutePathStopEndId, id = id)
    }

    fun updateGUIRoutePathStopEndDistanceById(id: Int, guiRoutePathStopEndDistance: Double) {
        val columnName = "gui_route_path_stop_end_distance"
        daoUtil.executeUpdateById(columnName, guiRoutePathStopEndDistance, id)
    }

    fun updateGUIRoutePathStopEndTimeById(id: Int, guiRoutePathStopEndTime: String) {
        val columnName = "gui_route_path_stop_end_time"
        daoUtil.executeUpdateById(columnName = columnName, value = guiRoutePathStopEndTime, id = id)
    }

    fun updateGUIRouteNextSubpathIdById(id: Int, guiRouteNextSubpathId: Int) {
        val columnName = "gui_route_next_subpath_id"
        daoUtil.executeUpdateById(columnName = columnName, value = guiRouteNextSubpathId, id = id)
    }

    fun updateGUIRouteNextSubpathDistanceById(id: Int, guiRouteNextSubpathDistance: Double) {
        val columnName = "gui_route_next_subpath_distance"
        daoUtil.executeUpdateById(columnName = columnName, value = guiRouteNextSubpathDistance, id = id)
    }

    fun updateGUIRouteNextSubpathTimeById(id: Int, guiRouteNextSubpathTime: String) {
        val columnName = "gui_route_next_subpath_time"
        daoUtil.executeUpdateById(columnName = columnName, value = guiRouteNextSubpathTime, id = id)
    }

    fun updateGUIRouteNextStopIdById(id: Int, guiRouteNextStopId: Int) {
        val columnName = "gui_route_next_stop_id"
        daoUtil.executeUpdateById(columnName = columnName, value = guiRouteNextStopId, id = id)
    }

    fun updateGUIRouteNextStopTimeById(id: Int, guiRouteNextStopTime: String) {
        val columnName = "gui_route_next_stop_time"
        daoUtil.executeUpdateById(columnName = columnName, value = guiRouteNextStopTime, id = id)
    }

    fun updateGUIRoutePSOIdById(id: Int, guiRoutePSOId: Int) {
        val columnName = "gui_route_pso_id"
        daoUtil.executeUpdateById(columnName = columnName, value = guiRoutePSOId, id = id)
    }

    fun updateGUIRouteDetailServiceIdById(id: Int, guiRouteDetailServiceId: Int) {
        val columnName = "gui_route_detail_service_id"
        daoUtil.executeUpdateById(columnName = columnName, value = guiRouteDetailServiceId, id = id)
    }

    fun updateGUIRouteCurrentSubpathIdById(id: Int, guiRouteCurrentSubpathId: Int) {
        val columnName = "gui_route_current_subpath_id"
        daoUtil.executeUpdateById(columnName = columnName, value = guiRouteCurrentSubpathId, id = id)
    }

    fun updateGUIRouteAdvanceById(id: Int, guiRouteAdvance: Double) {
        val columnName = "gui_route_advance"
        daoUtil.executeUpdateById(columnName = columnName, value = guiRouteAdvance, id = id)
    }

    fun updateGUIRouteStateSinopticById(id: Int, guiRouteStateSinoptic: Int) {
        val columnName = "gui_route_state_sinoptic"
        daoUtil.executeUpdateById(columnName = columnName, value = guiRouteStateSinoptic, id = id)
    }

    fun updateGUIRouteInformationById(id: Int, guiRouteInformation: String) {
        val columnName = "gui_route_information"
        daoUtil.executeUpdateById(columnName = columnName, value = guiRouteInformation, id = id)
    }

    fun updateGUIRouteServiceStopsById(id: Int, guiRouteDetailServiceStops: String) {
        val columnName = "gui_route_detail_service_stops"
        daoUtil.executeUpdateById(columnName = columnName, value = guiRouteDetailServiceStops, id = id)
    }

    fun updateSESIdUserById(id: Int, sesIdUser: String) {
        val columnName = "ses_id_user"
        daoUtil.executeUpdateById(columnName = columnName, value = sesIdUser, id = id)
    }

    fun updateSESUserById(id: Int, sesUser: String) {
        val columnName = "ses_user"
        daoUtil.executeUpdateById(columnName = columnName, value = sesUser, id = id)
    }

    fun updateSESTokenById(id: Int, sesToken: String) {
        val columnName = "ses_token"
        daoUtil.executeUpdateById(columnName = columnName, value = sesToken, id = id)
    }
}