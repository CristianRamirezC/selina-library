package com.dashfleet.selinaLibrary.data.repository

import com.dashfleet.selinaLibrary.data.database.dao.TransactionsDao
import com.dashfleet.selinaLibrary.data.database.entities.TransactionEntity
import com.dashfleet.selinaLibrary.data.model.transaction.TransactionRequestBodyModel
import com.dashfleet.selinaLibrary.data.model.transaction.TransactionResponseModel
import com.dashfleet.selinaLibrary.data.network.HttpRequest
import com.google.gson.Gson

class TransactionRepository {

    private val transactionDao = TransactionsDao()
    private val httpRequest: HttpRequest = HttpRequest()
    private val gson = Gson()

    fun getTransactionSAE(getTransactionRequestBody: TransactionRequestBodyModel): TransactionResponseModel {
        try {
            val endpoint = "v1/frame-selina"
            val response = httpRequest.postJSON(
                endpoint = endpoint,
                body = getTransactionRequestBody
            )

            return gson.fromJson(response, TransactionResponseModel::class.java)
        } catch (e: Exception) {
            return TransactionResponseModel()
        }
    }

    fun insertTransaction(transaction: TransactionEntity) {
        try {
            transactionDao.insertTransaction(transaction)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun getAllTransactions() {
        try {
            transactionDao.getAllTransactions()
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun getTransactionsQuantityByState(state: Boolean) {
        try {
            transactionDao.getTransactionsQuantityByState(state)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun getFirstTransactionByState(state: Boolean): TransactionEntity {
        return try {
            transactionDao.getFirstTransactionByState(state)
        } catch (e: Exception) {
            println(e.stackTraceToString())
            TransactionEntity()
        }
    }

    fun getTransactionsByTypeAndLastCloseTime(type: String, lastCloseTime: Int): List<TransactionEntity> {
        return try {
            transactionDao.getTransactionsByTypeAndLastCloseTime(type, lastCloseTime)
        } catch (e: Exception) {
            println(e.stackTraceToString())
            listOf()
        }
    }

    fun updateTransactionStateById(id: Int, state: Boolean) {
        try {
            transactionDao.updateTransactionStateById(id, state)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateTransactionKeyById(id: Int, key: String) {
        try {
            transactionDao.updateTransactionKeyById(id, key)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateTransactionKeyByType(type: String, key: String) {
        try {
            transactionDao.updateTransactionKeyByType(type, key)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun getTransactionsByType(type: String): List<TransactionEntity> {
        return try {
            transactionDao.getTransactionsByType(type)
        } catch (e: Exception) {
            println(e.stackTraceToString())
            listOf()
        }
    }

    fun deleteTransactionsByType(type: String) {
        try {
            transactionDao.deleteTransactionsByType(type)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun getTransactionsByTypeAndKey(type: String, key: String): List<TransactionEntity> {
        return try {
            transactionDao.getTransactionsByTypeAndKey(type, key)
        } catch (e: Exception) {
            println(e.stackTraceToString())
            listOf()
        }
    }

    fun getTransactionsByState(state: Boolean): List<TransactionEntity> {
        return try {
            transactionDao.getTransactionsByState(state)
        } catch (e: Exception) {
            println(e.stackTraceToString())
            listOf()
        }
    }

    fun getTransactionsByRangesDate(initialDate: Long, finalDate: Long): List<TransactionEntity> {
        return try {
            transactionDao.getTransactionsByRangesDate(initialDate, finalDate)
        } catch (e: Exception) {
            println(e.stackTraceToString())
            listOf()
        }
    }

    fun getTransactionsByRangesDateAndState(
        initialDate: Long,
        finalDate: Long,
        state: Boolean
    ): List<TransactionEntity> {
        return try {
            transactionDao.getTransactionsByRangesDateAndState(
                initialDate,
                finalDate,
                state
            )
        } catch (e: Exception) {
            println(e.stackTraceToString())
            listOf()
        }
    }

    fun updateTransactionsStateByRangesDate(initialDate: Long, finalDate: Long, state: Boolean) {
        try {
            transactionDao.updateTransactionsStateByRangesDate(initialDate, finalDate, state)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateStateInAllTransactions(state: Boolean) {
        try {
            transactionDao.updateStateInAllTransactions(state)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun getTransactionsByDriverCodeClosedStateAndValid(
        driverCode: String,
        closed: Boolean,
        valid: Boolean
    ): List<TransactionEntity> {
        return try {
            transactionDao.getTransactionsByDriverCodeClosedStateAndValid(
                driverCode,
                closed,
                valid
            )
        } catch (e: Exception) {
            println(e.stackTraceToString())
            listOf()
        }
    }

    fun updateTransactionClosedStatusByType(type: String, closed: Boolean) {
        try {
            transactionDao.updateTransactionClosedStatusByType(type, closed)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateTransactionClosedStatusByTypeAndDriverCode(type: String, driverCode: String, closed: Boolean) {
        try {
            transactionDao.updateTransactionClosedStatusByTypeAndDriverCode(type, driverCode, closed)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateTransactionClosedStatusByTypeDriverCodeAndValid(
        type: String,
        driverCode: String,
        valid: Boolean,
        closed: Boolean
    ) {
        try {
            transactionDao.updateTransactionClosedStatusByTypeDriverCodeAndValid(
                type,
                driverCode,
                valid,
                closed
            )
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateTransactionDataById(id: Int, data: String) {
        try {
            transactionDao.updateTransactionDataById(id, data)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun deleteTransactionsByDate(date: String) {
        try {
            transactionDao.deleteTransactionsByDate(date)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateTransactionClosedStatusByTransactionTypeAndDriverCode(
        typeOne: String,
        typeTwo: String,
        typeThree: String,
        driverCode: String,
        closed: Boolean
    ) {
        try {
            transactionDao.updateTransactionClosedStatusByTransactionTypeAndDriverCode(
                typeOne,
                typeTwo,
                typeThree,
                driverCode,
                closed
            )
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun getDataCreateLastSale(typeOne: String, typeTwo: String) {
        try {
            transactionDao.getDataCreateLastSale(typeOne, typeTwo)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

}