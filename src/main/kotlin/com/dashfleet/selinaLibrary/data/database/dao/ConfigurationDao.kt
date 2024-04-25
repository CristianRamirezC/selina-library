package com.dashfleet.selinaLibrary.data.database.dao

import com.dashfleet.selinaLibrary.data.database.HibernateSession
import com.dashfleet.selinaLibrary.data.database.entities.ConfigurationEntity

class ConfigurationDao {

    private val tableNameEntity = "ConfigurationEntity"

    fun getConfigurationByID(id: Int): ConfigurationEntity {
        HibernateSession.initHibernateSession()
        val entityToReturn = HibernateSession.session.get(ConfigurationEntity::class.java, id)
        HibernateSession.closeHibernateSession()
        return entityToReturn
    }

    fun storeConfiguration(entityToStore: ConfigurationEntity) {
        HibernateSession.initHibernateSession()
        HibernateSession.session.save(entityToStore)
        HibernateSession.closeHibernateSession()
    }

    fun updateConfigurationById(id: Int, time: String) {
        HibernateSession.initHibernateSession()
        val queryString = "UPDATE $tableNameEntity SET time =:time WHERE id =:id"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("time", time)
        query.setParameter("id", id)
        query.executeUpdate()
        HibernateSession.closeHibernateSession()
    }


}