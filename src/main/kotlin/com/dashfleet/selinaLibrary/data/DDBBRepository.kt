package com.dashfleet.selinaLibrary.data

import com.dashfleet.selinaLibrary.data.database.dao.ConfigurationDao
import com.dashfleet.selinaLibrary.data.database.entities.ConfigurationEntity

class DDBBRepository {

    private val configurationDao = ConfigurationDao()

    fun storeConfigInDB(configurationToStore: ConfigurationEntity) {
        try {
            configurationDao.storeConfiguration(configurationToStore)
        } catch (e: Exception) {
            //TODO
        }
    }

    fun getConfigByIDDB(id: Int): ConfigurationEntity {
        return try {
            configurationDao.getConfigurationByID(id)
        } catch (e: Exception) {
            println(e.stackTraceToString())
            ConfigurationEntity()
        }
    }

    fun updateConfigById(id: Int, time: String) {
        try {
            configurationDao.updateConfigurationById(id = id, time = time)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }


}