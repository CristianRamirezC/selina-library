package com.dashfleet.selinaLibrary.data.database.dao

import com.dashfleet.selinaLibrary.data.database.DAOUtils
import com.dashfleet.selinaLibrary.data.database.HibernateSession
import com.dashfleet.selinaLibrary.data.database.entities.ConfigurationEntity

class ConfigurationDao {

    private val daoUtil = DAOUtils(ConfigurationEntity::class.java)
    private val tableName = "configuration"

    fun getConfigurationByID(id: Int): ConfigurationEntity {
        return daoUtil.executeSelectById(id)
    }

    fun storeConfiguration(entityToStore: ConfigurationEntity) {
        daoUtil.executeInsert(entityToStore)
    }

    fun updateConfigurationTimeById(id: Int, time: String) {
        val columnName = "time"
        daoUtil.executeUpdateById(columnName, time, id)
    }


}