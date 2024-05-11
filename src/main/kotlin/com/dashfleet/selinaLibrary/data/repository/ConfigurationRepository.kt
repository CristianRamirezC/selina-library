package com.dashfleet.selinaLibrary.data.repository

import com.dashfleet.selinaLibrary.data.database.util.HibernateSession
import com.dashfleet.selinaLibrary.data.database.dao.ConfigurationDao
import com.dashfleet.selinaLibrary.data.database.entities.ConfigurationEntity
import com.dashfleet.selinaLibrary.data.model.configuration.ConfigRequestBodyModel
import com.dashfleet.selinaLibrary.data.model.configuration.ConfigResponseModel
import com.dashfleet.selinaLibrary.data.network.HttpRequest
import com.google.gson.Gson

class ConfigurationRepository {

    private val configurationDao = ConfigurationDao()
    private val httpRequest: HttpRequest = HttpRequest()
    private val gson = Gson()

    //Get SAE configuration
    fun getConfigurationSAE(getConfigurationRequestBody: ConfigRequestBodyModel): ConfigResponseModel {
        try {
            val endpoint = "v1/config-selina"
            val response = httpRequest.postJSON(
                endpoint = endpoint,
                body = getConfigurationRequestBody
            )

            return gson.fromJson(response, ConfigResponseModel::class.java)
        } catch (e: Exception) {
            //TODO
            return ConfigResponseModel()
        }
    }

    fun storeConfig(configurationToStore: ConfigurationEntity) {
        try {
            configurationDao.storeConfiguration(configurationToStore)
        } catch (e: Exception) {
            println(e.stackTraceToString())
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

    fun getAllConfigs(): List<ConfigurationEntity> {
        return try {
            configurationDao.getAllConfigs()
        } catch (e: Exception) {
            println(e.stackTraceToString())
            listOf()
        }
    }

    fun getTransactionsQuantityByState(id: Int): Long {
        HibernateSession.initHibernateSession()
        val sqlQuery = "SELECT COUNT($id) FROM ConfigurationEntity"
        val query = HibernateSession.session.createQuery(sqlQuery)
        val result = query.uniqueResult()
        HibernateSession.closeHibernateSession()
        return result as Long
    }


}