package com.dashfleet.selinaLibrary.data.repository

import com.dashfleet.selinaLibrary.data.database.dao.StatesDao
import com.dashfleet.selinaLibrary.data.database.entities.StatesEntity

class StatesRepository {

    private val statesDao = StatesDao()

    fun saveStates(stateToStore: StatesEntity) {
        try {
            statesDao.storeState(stateToStore)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun getAllStatesFrom(): List<StatesEntity> {
        return try {
            statesDao.getAllStates()
        } catch (e: Exception) {
            println(e.stackTraceToString())
            listOf()
        }
    }

    fun updateGPSStateById(id: Int, gpsState: Boolean) {
        try {
            statesDao.updateGPSStateById(id = id, gpsState = gpsState)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGpsLatitudeById(id: Int, latitude: Double) {
        try {
            statesDao.updateGpsLatitudeById(id = id, latitude = latitude)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGpsLongitudeById(id: Int, longitude: Double) {
        try {
            statesDao.updateGpsLongitudeById(id = id, longitude = longitude)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGpsSpeedById(id: Int, speed: Double) {
        try {
            statesDao.updateGpsSpeedById(id = id, speed = speed)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGpsCourseById(id: Int, course: Double) {
        try {
            statesDao.updateGpsCourseById(id = id, course = course)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGpsSatellitesById(id: Int, satellites: Int) {
        try {
            statesDao.updateGpsSatellitesById(id = id, satellites = satellites)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGpsDatetimeById(id: Int, datetime: String) {
        try {
            statesDao.updateGpsDatetimeById(id = id, datetime = datetime)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateDoorOneStatusById(id: Int, doorOneStatus: String) {
        try {
            statesDao.updateDoorOneStatusById(id = id, doorOneStatus = doorOneStatus)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateDoorTwoStatusById(id: Int, doorTwoStatus: String) {
        try {
            statesDao.updateDoorTwoStatusById(id = id, doorTwoStatus = doorTwoStatus)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateDoorTreeStatusById(id: Int, doorTreeStatus: String) {
        try {
            statesDao.updateDoorTreeStatusById(id = id, doorThreeStatus = doorTreeStatus)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateIgnitionStatusById(id: Int, ignitionStatus: String) {
        try {
            statesDao.updateIgnitionStatusById(id = id, ignitionStatus = ignitionStatus)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updatePanicStatusById(id: Int, panicStatus: String) {
        try {
            statesDao.updatePanicStatusById(id = id, panicStatus = panicStatus)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateModemNetworkTypeById(id: Int, modemNetworkType: String) {
        try {
            statesDao.updateModemNetworkTypeById(id = id, modemNetworkType = modemNetworkType)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateModemSignalLevelById(id: Int, modemSignalLevel: Int) {
        try {
            statesDao.updateModemSignalLevelById(id = id, modemSignalLevel = modemSignalLevel)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateModemSNRLevelById(id: Int, modemSNRLevel: Int) {
        try {
            statesDao.updateModemSNRLevelById(id = id, modemSNRLevel = modemSNRLevel)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateModemStateById(id: Int, modemState: Boolean) {
        try {
            statesDao.updateModemStateById(id = id, modemState = modemState)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateDisplayStateById(id: Int, displayState: Boolean) {
        try {
            statesDao.updateDisplayStateById(id = id, displayState = displayState)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateTurnstileStateById(id: Int, turnstileState: Boolean) {
        try {
            statesDao.updateTurnstileStateById(id = id, turnstileState = turnstileState)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateAPCStateById(id: Int, apcState: Boolean) {
        try {
            statesDao.updateAPCStateById(id = id, apcState = apcState)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateAPCInCounterById(id: Int, apcInCounter: Long) {
        try {
            statesDao.updateAPCInCounterById(id = id, apcInCounter = apcInCounter)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateAPCOutCounterById(id: Int, apcOutCounter: Long) {
        try {
            statesDao.updateAPCOutCounterById(id = id, apcOutCounter = apcOutCounter)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateOdoCounterById(id: Int, odoCounter: Float) {
        try {
            statesDao.updateOdoCounterById(id = id, odoCounter = odoCounter)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGUIRouteStateById(id: Int, guiRouteState: Int) {
        try {
            statesDao.updateGUIRouteStateById(id = id, guiRouteState = guiRouteState)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGUIRouteIdById(id: Int, guiRouteId: Int) {
        try {
            statesDao.updateGUIRouteIdById(id = id, guiRouteId = guiRouteId)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGUIRouteIdServiceById(id: Int, guiRouteIdService: Int) {
        try {
            statesDao.updateGUIRouteIdServiceById(id = id, guiRouteIdService = guiRouteIdService)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGUIRouteETABackById(id: Int, guiRouteETABack: Int) {
        try {
            statesDao.updateGUIRouteETABackById(id = id, guiRouteETABack = guiRouteETABack)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGUIRouteETAById(id: Int, guiRouteETA: Int) {
        try {
            statesDao.updateGUIRouteETAById(id = id, guiRouteETA = guiRouteETA)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGUIRouteETAFrontById(id: Int, guiRouteETAFront: Int) {
        try {
            statesDao.updateGUIRouteETAFrontById(id = id, guiRouteETAFront = guiRouteETAFront)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGUIRouteTotalDistanceById(id: Int, guiRouteTotalDistance: Double) {
        try {
            statesDao.updateGUIRouteTotalDistanceById(id = id, guiRouteTotalDistance = guiRouteTotalDistance)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGUIRouteNetworkById(id: Int, guiRouteNetwork: String) {
        try {
            statesDao.updateGUIRouteNetworkById(id = id, guiRouteNetwork = guiRouteNetwork)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGUIRouteTotalTimeById(id: Int, guiRouteTotalTime: String) {
        try {
            statesDao.updateGUIRouteTotalTimeById(id = id, guiRouteTotalTime = guiRouteTotalTime)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGUIRouteStopStartIdById(id: Int, guiRouteStopStartId: Int) {
        try {
            statesDao.updateGUIRouteStopStartIdById(id = id, guiRouteStopStartId = guiRouteStopStartId)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGUIRouteStopEndIdById(id: Int, guiRouteStopEndId: Int) {
        try {
            statesDao.updateGUIRouteStopEndIdById(id = id, guiRouteStopEndId = guiRouteStopEndId)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGUIRoutePathStopStartById(id: Int, guiRoutePathStopStartId: String) {
        try {
            statesDao.updateGUIRoutePathStopStartById(id = id, guiRoutePathStopStartId = guiRoutePathStopStartId)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGUIRoutePathStopEndById(id: Int, guiRoutePathStopEndId: String) {
        try {
            statesDao.updateGUIRoutePathStopEndById(id = id, guiRoutePathStopEndId = guiRoutePathStopEndId)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGUIRoutePathStopEndDistanceById(id: Int, guiRoutePathStopEndDistance: Double) {
        try {
            statesDao.updateGUIRoutePathStopEndDistanceById(
                id = id,
                guiRoutePathStopEndDistance = guiRoutePathStopEndDistance
            )
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGUIRoutePathStopEndTimeById(id: Int, guiRoutePathStopEndTime: String) {
        try {
            statesDao.updateGUIRoutePathStopEndTimeById(id = id, guiRoutePathStopEndTime = guiRoutePathStopEndTime)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGUIRouteNextSubpathIdById(id: Int, guiRouteNextSubpathId: Int) {
        try {
            statesDao.updateGUIRouteNextSubpathIdById(id = id, guiRouteNextSubpathId = guiRouteNextSubpathId)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGUIRouteNextSubpathDistanceById(id: Int, guiRouteNextSubpathDistance: Double) {
        try {
            statesDao.updateGUIRouteNextSubpathDistanceById(
                id = id,
                guiRouteNextSubpathDistance = guiRouteNextSubpathDistance
            )
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGUIRouteNextSubpathTimeById(id: Int, guiRouteNextSubpathTime: String) {
        try {
            statesDao.updateGUIRouteNextSubpathTimeById(id = id, guiRouteNextSubpathTime = guiRouteNextSubpathTime)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGUIRouteNextStopIdById(id: Int, guiRouteNextStopId: Int) {
        try {
            statesDao.updateGUIRouteNextStopIdById(id = id, guiRouteNextStopId = guiRouteNextStopId)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGUIRouteNextStopTimeById(id: Int, guiRouteNextStopTime: String) {
        try {
            statesDao.updateGUIRouteNextStopTimeById(id = id, guiRouteNextStopTime = guiRouteNextStopTime)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGUIRoutePSOIdById(id: Int, guiRoutePSOId: Int) {
        try {
            statesDao.updateGUIRoutePSOIdById(id = id, guiRoutePSOId = guiRoutePSOId)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGUIRouteDetailServiceIdById(id: Int, guiRouteDetailServiceId: Int) {
        try {
            statesDao.updateGUIRouteDetailServiceIdById(id = id, guiRouteDetailServiceId = guiRouteDetailServiceId)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGUIRouteCurrentSubpathIdById(id: Int, guiRouteCurrentSubpathId: Int) {
        try {
            statesDao.updateGUIRouteCurrentSubpathIdById(id = id, guiRouteCurrentSubpathId = guiRouteCurrentSubpathId)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGUIRouteAdvanceById(id: Int, guiRouteAdvance: Double) {
        try {
            statesDao.updateGUIRouteAdvanceById(id = id, guiRouteAdvance = guiRouteAdvance)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGUIRouteStateSinopticById(id: Int, guiRouteStateSinoptic: Int) {
        try {
            statesDao.updateGUIRouteStateSinopticById(id = id, guiRouteStateSinoptic = guiRouteStateSinoptic)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateGUIRouteInformationById(id: Int, guiRouteInformation: String) {
        try {
            statesDao.updateGUIRouteInformationById(id = id, guiRouteInformation = guiRouteInformation)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }
    fun updateGUIRouteServiceStopsById(id: Int, guiRouteDetailServiceStops: String) {
        try {
            statesDao.updateGUIRouteServiceStopsById(id = id, guiRouteDetailServiceStops = guiRouteDetailServiceStops)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateSESIdUserById(id: Int, sesIdUser: String) {
        try {
            statesDao.updateSESIdUserById(id = id, sesIdUser = sesIdUser)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateSESUserById(id: Int, sesUser: String) {
        try {
            statesDao.updateSESUserById(id = id, sesUser = sesUser)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateSESTokenById(id: Int, sesToken: String) {
        try {
            statesDao.updateSESTokenById(id = id, sesToken = sesToken)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

}