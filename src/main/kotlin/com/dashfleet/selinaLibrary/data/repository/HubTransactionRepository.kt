package com.dashfleet.selinaLibrary.data.repository

import com.dashfleet.selinaLibrary.data.database.dao.HubTransactionsDao
import com.dashfleet.selinaLibrary.data.database.entities.HubTransactionEntity

class HubTransactionRepository {

    private val hubTransactionDao = HubTransactionsDao()

    fun insertHubTransaction(hubTransaction: HubTransactionEntity) {
        try {
            hubTransactionDao.insertHubTransaction(hubTransaction)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun getResponseHubTransactions(): HubTransactionEntity {
        return try {
            hubTransactionDao.getResponseHubTransactions()
        } catch (e: Exception) {
            println(e.stackTraceToString())
            HubTransactionEntity()
        }
    }

    fun getRequestHubTransactions(): HubTransactionEntity {
        return try {
            hubTransactionDao.getRequestHubTransactions()
        } catch (e: Exception) {
            println(e.stackTraceToString())
            HubTransactionEntity()
        }
    }

    fun getRequestValidatorHubTransactions(): HubTransactionEntity {
        return try {
            hubTransactionDao.getRequestValidatorHubTransactions()
        } catch (e: Exception) {
            println(e.stackTraceToString())
            HubTransactionEntity()
        }
    }

    fun updateRequestValidatorHubTransactions(id: Int) {
        try {
            hubTransactionDao.updateRequestValidatorHubTransactions(id)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateRequestHubTransactionState(id: Int) {
        try {
            hubTransactionDao.updateRequestHubTransactionState(id)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateResponseHubTransactionState(id: Int) {
        try {
            hubTransactionDao.updateResponseHubTransactionState(id)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun getRequestMaxID(): HubTransactionEntity {
        return try {
            hubTransactionDao.getRequestMaxID()
        } catch (e: Exception) {
            println(e.stackTraceToString())
            HubTransactionEntity()
        }
    }

    fun getResponseMaxID(): HubTransactionEntity {
        return try {
            hubTransactionDao.getResponseMaxID()
        } catch (e: Exception) {
            println(e.stackTraceToString())
            HubTransactionEntity()
        }
    }

    fun getResponseValidator(transactionId: String): HubTransactionEntity {
        return try {
            hubTransactionDao.getResponseValidator(transactionId)
        } catch (e: Exception) {
            println(e.stackTraceToString())
            HubTransactionEntity()
        }
    }

    fun resetAllTransactions() {
        try {
            hubTransactionDao.resetAllTransactions()
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun deleteResponseTransactions() {
        try {
            hubTransactionDao.deleteResponseTransactions()
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun deleteHubTransactions() {
        try {
            hubTransactionDao.deleteHubTransactions()
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun getTransactionCount() {
        try {
            hubTransactionDao.getTransactionCount()
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }
}