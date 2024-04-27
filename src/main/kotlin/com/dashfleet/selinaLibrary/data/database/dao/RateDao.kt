package com.dashfleet.selinaLibrary.data.database.dao

import com.dashfleet.selinaLibrary.data.database.entities.NotificationEntity
import com.dashfleet.selinaLibrary.data.database.entities.RateEntity
import com.dashfleet.selinaLibrary.data.database.util.DAOUtils

class RateDao {

    private val daoUtils = DAOUtils(RateEntity::class.java)
    private val tableName = "rates"
    private val tableNameEntity = "RateEntity"

    fun insertRate(rate: RateEntity) {
        daoUtils.executeInsert(rate)
    }

    fun getAllRates(): List<RateEntity> {
        return daoUtils.executeSelectAll("rates")
    }

    fun deleteAllRates() {
        val queryString = "DELETE FROM $tableNameEntity"
        daoUtils.executeCustomModifyingQuery(queryString)
    }

}