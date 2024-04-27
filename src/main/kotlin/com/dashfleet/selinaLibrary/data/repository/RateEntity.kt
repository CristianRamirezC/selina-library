package com.dashfleet.selinaLibrary.data.repository

import com.dashfleet.selinaLibrary.data.database.dao.RateDao
import com.dashfleet.selinaLibrary.data.database.entities.RateEntity

class RateEntity {

    private val rateDao = RateDao()

    fun insertRate(rate: RateEntity) {
        try {
            rateDao.insertRate(rate)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun getAllRates(): List<RateEntity> {
        return try {
            rateDao.getAllRates()
        } catch (e: Exception) {
            println(e.stackTraceToString())
            listOf()
        }
    }

    fun deleteAllRates() {
        try {
            rateDao.deleteAllRates()
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

}