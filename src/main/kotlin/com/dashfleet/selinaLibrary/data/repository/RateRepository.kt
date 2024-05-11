package com.dashfleet.selinaLibrary.data.repository

import com.dashfleet.selinaLibrary.data.database.dao.RateDao
import com.dashfleet.selinaLibrary.data.database.entities.RateEntity
import com.dashfleet.selinaLibrary.data.model.rates.GetRatesResponseModel
import com.dashfleet.selinaLibrary.data.network.HttpRequest
import com.google.gson.Gson

class RateRepository {

    private val rateDao = RateDao()
    private val httpRequest: HttpRequest = HttpRequest()
    private val gson = Gson()


    fun getRatesSAE(): GetRatesResponseModel {
        try {
            val endpoint = "rate-managements"
            val response = httpRequest.getJSON(endpoint = endpoint)

            return gson.fromJson(response, GetRatesResponseModel::class.java)
        } catch (e: Exception) {
            return GetRatesResponseModel()
        }
    }

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