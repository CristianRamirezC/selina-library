package com.dashfleet.selinaLibrary.data.database.dao

import com.dashfleet.selinaLibrary.data.database.HibernateSession
import com.dashfleet.selinaLibrary.data.database.entities.StatesEntity

class StatesDao {

    private val tableNameEntity = "StatesEntity"

    /** Insert a state in DD BB **/
    fun storeState(entityToStore: StatesEntity) {
        HibernateSession.initHibernateSession()
        HibernateSession.session.save(entityToStore)
        HibernateSession.closeHibernateSession()
    }

    fun getAllStates(): List<StatesEntity> {
        HibernateSession.initHibernateSession()
        val queryString = "FROM states"
        val query = HibernateSession.session.createQuery(
            queryString, StatesEntity::class.java
        ).resultList
        HibernateSession.closeHibernateSession()
        return query
    }

    ////////////////////////////////GPS functions//////////////////////////////////

    fun updateGPSStateById(id: Int, gpsState: Boolean) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gps_state =:gpsState WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("gpsState", gpsState)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGpsLatitudeById(id: Int, latitude: Double) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gps_latitude =:latitude WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("latitude", latitude)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGpsLongitudeById(id: Int, longitude: Double) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gps_longitude =:longitude WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("longitude", longitude)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGpsSpeedById(id: Int, speed: Double) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gps_speed =:speed WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("speed", speed)
        query.setParameter("id", id)
        HibernateSession.closeHibernateSession()
    }

    fun updateGpsCourseById(id: Int, course: Double) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gps_course =:course WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("id", id)
        query.setParameter("course", course)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGpsSatellitesById(id: Int, satellites: Int) {
        HibernateSession.initHibernateSession()
        val queryString = "UPDATE $tableNameEntity SET gps_satellites =:satellites WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("satellites", satellites)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGpsDatetimeById(id: Int, datetime: String) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gps_datetime =:datetime WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("datetime", datetime)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    ////////////////////////////////IOS functions//////////////////////////////////

    fun updateDoorOneStatusById(id: Int, doorOneStatus: String) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET io_door_one =:doorOneStatus WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("doorOneStatus", doorOneStatus)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateDoorTwoStatusById(id: Int, doorTwoStatus: String) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET modem_signal_level =:modemSignalLevel WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("doorTwoStatus", doorTwoStatus)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateDoorTreeStatusById(id: Int, doorThreeStatus: String) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET io_door_tree =:doorThreeStatus WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("doorThreeStatus", doorThreeStatus)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateIgnitionStatusById(id: Int, ignitionStatus: String) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET io_ignition =:ignitionStatus WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("ignitionStatus", ignitionStatus)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updatePanicStatusById(id: Int, panicStatus: String) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET io_panic =:panicStatus WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("panicStatus", panicStatus)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    ////////////////////////////////Modem functions//////////////////////////////////

    fun updateModemNetworkTypeById(id: Int, modemNetworkType: String) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET modem_network_type =:modemNetworkType WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("modemNetworkType", modemNetworkType)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateModemSignalLevelById(id: Int, modemSignalLevel: Int) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET modem_signal_level =:modemSignalLevel WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("modemSignalLevel", modemSignalLevel)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateModemSNRLevelById(id: Int, modemSNRLevel: Int) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET modem_snr_level =:modemSNRLevel WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("modemSNRLevel", modemSNRLevel)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateModemStateById(id: Int, modemState: Boolean) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET modem_state =:modemState WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("modemState", modemState)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    ////////////////////////////////Display functions//////////////////////////////////

    fun updateDisplayStateById(id: Int, displayState: Boolean) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET display_state =:displayState WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("displayState", displayState)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    ///////////////////////////////Turnstile functions/////////////////////////////////

    fun updateTurnstileStateById(id: Int, turnstileState: Boolean) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET turnstile_state =:turnstileState WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("turnstileState", turnstileState)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    /////////////////////////////////Camera functions//////////////////////////////////

    fun updateAPCStateById(id: Int, apcState: Boolean) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET apc_state =:apcState WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("apcState", apcState)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateAPCInCounterById(id: Int, apcInCounter: Long) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET apc_in =:apcInCounter WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("apcInCounter", apcInCounter)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateAPCOutCounterById(id: Int, apcOutCounter: Long) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET apc_out =:apcOutCounter WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("apcOutCounter", apcOutCounter)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    /////////////////////////////////Odometer functions//////////////////////////////////

    fun updateOdoCounterById(id: Int, odoCounter: Float) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET odo_count =:odoCounter WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("odoCounter", odoCounter)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    //////////////////////////////////Route functions//////////////////////////////////

    fun updateGUIRouteStateById(id: Int, guiRouteState: Int) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gui_route_state =:guiRouteState WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("guiRouteState", guiRouteState)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGUIRouteIdById(id: Int, guiRouteId: Int) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE states SET gui_route_id =:guiRouteId WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("guiRouteId", guiRouteId)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGUIRouteIdServiceById(id: Int, guiRouteIdService: Int) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gui_route_id_service =:guiRouteIdService WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("guiRouteIdService", guiRouteIdService)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGUIRouteETABackById(id: Int, guiRouteETABack: Int) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gui_route_eta_back =:guiRouteETABack WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("guiRouteETABack", guiRouteETABack)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGUIRouteETAById(id: Int, guiRouteETA: Int) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE states SET gui_route_eta =:guiRouteETA WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("guiRouteETA", guiRouteETA)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGUIRouteETAFrontById(id: Int, guiRouteETAFront: Int) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gui_route_eta_front =:guiRouteETAFront WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("guiRouteETAFront", guiRouteETAFront)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGUIRouteTotalDistanceById(id: Int, guiRouteTotalDistance: Double) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gui_route_total_distance =:guiRouteTotalDistance WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("guiRouteTotalDistance", guiRouteTotalDistance)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGUIRouteNetworkById(id: Int, guiRouteNetwork: String) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gui_route_network =:guiRouteNetwork WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("guiRouteNetwork", guiRouteNetwork)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGUIRouteTotalTimeById(id: Int, guiRouteTotalTime: String) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gui_route_total_time =:guiRouteTotalTime WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("guiRouteTotalTime", guiRouteTotalTime)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGUIRouteStopStartIdById(id: Int, guiRouteStopStartId: Int) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gui_route_stop_start_id =:guiRouteStopStartId WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("guiRouteStopStartId", guiRouteStopStartId)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGUIRouteStopEndIdById(id: Int, guiRouteStopEndId: Int) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gui_route_stop_end_id =:guiRouteStopEndId WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("guiRouteStopEndId", guiRouteStopEndId)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGUIRoutePathStopStartById(id: Int, guiRoutePathStopStartId: String) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gui_route_path_stop_start_id =:guiRoutePathStopStartId WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("guiRoutePathStopStartId", guiRoutePathStopStartId)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGUIRoutePathStopEndById(id: Int, guiRoutePathStopEndId: String) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gui_route_path_stop_end_id =:guiRoutePathStopEndId WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("guiRoutePathStopEndId", guiRoutePathStopEndId)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGUIRoutePathStopEndDistanceById(id: Int, guiRoutePathStopEndDistance: Double) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gui_route_path_stop_end_distance =:guiRoutePathStopEndDistance WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("guiRoutePathStopEndDistance", guiRoutePathStopEndDistance)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGUIRoutePathStopEndTimeById(id: Int, guiRoutePathStopEndTime: String) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gui_route_path_stop_end_time =:guiRoutePathStopEndTime WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("guiRoutePathStopEndTime", guiRoutePathStopEndTime)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGUIRouteNextSubpathIdById(id: Int, guiRouteNextSubpathId: Int) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gui_route_next_subpath_id =:guiRouteNextSubpathId WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("guiRouteNextSubpathId", guiRouteNextSubpathId)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGUIRouteNextSubpathDistanceById(id: Int, guiRouteNextSubpathDistance: Double) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gui_route_next_subpath_distance =:guiRouteNextSubpathDistance WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("guiRouteNextSubpathDistance", guiRouteNextSubpathDistance)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGUIRouteNextSubpathTimeById(id: Int, guiRouteNextSubpathTime: String) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gui_route_next_subpath_time =:guiRouteNextSubpathTime WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("guiRouteNextSubpathTime", guiRouteNextSubpathTime)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGUIRouteNextStopIdById(id: Int, guiRouteNextStopId: Int) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gui_route_next_stop_id =:guiRouteNextStopId WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("guiRouteNextStopId", guiRouteNextStopId)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGUIRouteNextStopTimeById(id: Int, guiRouteNextStopTime: String) {
        HibernateSession.initHibernateSession()
        val queryString = "UPDATE $tableNameEntity SET gui_route_next_stop_time =:guiRouteNextStopTime WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("guiRouteNextStopTime", guiRouteNextStopTime)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGUIRoutePSOIdById(id: Int, guiRoutePSOId: Int) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gui_route_pso_id =:guiRoutePSOId WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("guiRoutePSOId", guiRoutePSOId)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGUIRouteDetailServiceIdById(id: Int, guiRouteDetailServiceId: Int) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gui_route_detail_service_id =:guiRouteDetailServiceId WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("guiRouteDetailServiceId", guiRouteDetailServiceId)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGUIRouteCurrentSubpathIdById(id: Int, guiRouteCurrentSubpathId: Int) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gui_route_current_subpath_id =:guiRouteCurrentSubpathId WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("guiRouteCurrentSubpathId", guiRouteCurrentSubpathId)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGUIRouteAdvanceById(id: Int, guiRouteAdvance: Double) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gui_route_advance =:guiRouteAdvance WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("guiRouteAdvance", guiRouteAdvance)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGUIRouteStateSinopticById(id: Int, guiRouteStateSinoptic: Int) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gui_route_state_sinoptic =:guiRouteStateSinoptic WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("guiRouteStateSinoptic", guiRouteStateSinoptic)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGUIRouteInformationById(id: Int, guiRouteInformation: String) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gui_route_information =:guiRouteInformation WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("guiRouteInformation", guiRouteInformation)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateGUIRouteServiceStopsById(id: Int, guiRouteDetailServiceStops: String) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET gui_route_detail_service_stops =:guiRouteDetailServiceStops WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("guiRouteDetailServiceStops", guiRouteDetailServiceStops)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateSESIdUserById(id: Int, sesIdUser: String) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET ses_id_user =:sesIdUser WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("sesIdUser", sesIdUser)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateSESUserById(id: Int, sesUser: String) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET ses_user =:sesUser WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("sesUser", sesUser)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }

    fun updateSESTokenById(id: Int, sesToken: String) {
        HibernateSession.initHibernateSession()
        val queryString =
            "UPDATE $tableNameEntity SET ses_token =:sesToken WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("sesToken", sesToken)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }
}