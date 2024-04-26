package com.dashfleet.selinaLibrary.data.database.repository

import com.dashfleet.selinaLibrary.data.database.dao.ConfigurationDao
import com.dashfleet.selinaLibrary.data.database.entities.ConfigurationEntity

class ConfigurationRepository {

    private val configurationDao = ConfigurationDao()

    fun storeConfig(configurationToStore: ConfigurationEntity) {
        try {
            configurationDao.storeConfiguration(configurationToStore)
        } catch (e: Exception) {
            //TODO
        }
    }

    fun getConfigByID(id: Int): ConfigurationEntity {
        return try {
            configurationDao.getConfigurationByID(id)
        } catch (e: Exception) {
            println(e.stackTraceToString())
            ConfigurationEntity()
        }
    }

    fun updateConfigTimeById(id: Int, time: String) {
        try {
            configurationDao.updateConfigurationTimeById(id = id, time = time)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }


}