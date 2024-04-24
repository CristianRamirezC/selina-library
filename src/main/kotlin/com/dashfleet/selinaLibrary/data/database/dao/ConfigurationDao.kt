package com.dashfleet.selinaLibrary.data.database.dao

import com.dashfleet.selinaLibrary.data.database.HibernateUtil
import com.dashfleet.selinaLibrary.data.database.entities.ConfigurationEntity

class ConfigurationDao {

    private fun initHibernateSession() {
        HibernateUtil.buildSessionFactory()
        HibernateUtil.openSession()
    }

    fun getConfigurationByID(id: Int): ConfigurationEntity {
        initHibernateSession()

        val session = HibernateUtil.getCurrentSession()
        val transaction = session.beginTransaction()

        val entityClass = ConfigurationEntity::class.java
        val entityToReturn = session.get(entityClass, id)

        transaction.commit()
        session.close()

        return entityToReturn
    }

    fun storeConfiguration(entityToStore: ConfigurationEntity) {
        initHibernateSession()

        val session = HibernateUtil.getCurrentSession()
        val transaction = session.beginTransaction()

        session.save(entityToStore)

        transaction.commit()
        session.close()
    }



}