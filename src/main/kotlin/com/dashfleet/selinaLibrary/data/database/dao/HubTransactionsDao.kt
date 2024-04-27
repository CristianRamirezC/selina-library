package com.dashfleet.selinaLibrary.data.database.dao

import com.dashfleet.selinaLibrary.data.database.util.DAOUtils
import com.dashfleet.selinaLibrary.data.database.util.HibernateSession
import com.dashfleet.selinaLibrary.data.database.entities.HubTransactionEntity

class HubTransactionsDao {

    private val daoUtil = DAOUtils(HubTransactionEntity::class.java)
    private val tableName = "hubTransaction"
    private val tableNameEntity = "HubTransactionEntity"

    fun insertHubTransaction(hubTransaction: HubTransactionEntity) {
        daoUtil.executeInsert(hubTransaction)
    }

    fun getResponseHubTransactions(): HubTransactionEntity {
        val queryString = "FROM $tableNameEntity " +
                "WHERE request_type = 'Response' AND " +
                "state = 'false' " +
                "ORDER BY id " +
                "ASC LIMIT 1"
        val queryResult = daoUtil.executeCustomSelectQuery(queryString)
        return if (queryResult.isNotEmpty()) {
            queryResult.first()
        } else {
            HubTransactionEntity()
        }
    }

    fun getRequestHubTransactions(): HubTransactionEntity {
        val queryString = "FROM $tableNameEntity " +
                "WHERE request_type = 'Request' AND " +
                "NOT(device = 'Validator' AND request = 'Open') AND " +
                "state = 'false' " +
                "ORDER BY id ASC LIMIT 1"
        val queryResult = daoUtil.executeCustomSelectQuery(queryString)

        return if (queryResult.isNotEmpty()) {
            queryResult.first()
        } else {
            HubTransactionEntity()
        }
    }

    fun getRequestValidatorHubTransactions(): HubTransactionEntity {
        val queryString = "FROM $tableNameEntity " +
                "WHERE device = 'Validator' AND " +
                "request_type = 'Request' AND " +
                "request = 'Open' AND " +
                "state = 'false' " +
                "ORDER BY id ASC LIMIT 1"
        val queryResult = daoUtil.executeCustomSelectQuery(queryString)

        return if (queryResult.isNotEmpty()) {
            queryResult.first()
        } else {
            HubTransactionEntity()
        }
    }

    fun updateRequestValidatorHubTransactions(id: Int) {
        val queryString = "UPDATE $tableNameEntity " +
                "SET state = 'true' " +
                "WHERE request_type = 'Request' AND" +
                " id = $id"
        daoUtil.executeCustomModifyingQuery(queryString)
    }

    fun updateRequestHubTransactionState(id: Int) {
        val queryString = "UPDATE $tableNameEntity " +
                "SET state = 'true' " +
                "WHERE request_type = 'Request' AND" +
                " id = $id"
        daoUtil.executeCustomModifyingQuery(queryString)
    }

    fun updateResponseHubTransactionState(id: Int) {
        val queryString = "UPDATE $tableNameEntity " +
                "SET state = 'true' " +
                "WHERE request_type = 'Response' AND" +
                " id = $id"
        daoUtil.executeCustomModifyingQuery(queryString)
    }

    fun getRequestMaxID(): HubTransactionEntity {
        val queryString = "FROM $tableNameEntity " +
                "WHERE request_type = 'Request' AND " +
                "NOT(device = 'Validator' AND request = 'Open') " +
                "ORDER BY id DESC LIMIT 1"
        val requestResult = daoUtil.executeCustomSelectQuery(queryString)

        return if (requestResult.isNotEmpty()) {
            requestResult.first()
        } else {
            HubTransactionEntity()
        }
    }

    fun getResponseMaxID(): HubTransactionEntity {
        val queryString = "FROM $tableNameEntity " +
                "WHERE request_type = 'Response' AND " +
                "NOT(device = 'Validator' AND request = 'Open') " +
                "ORDER BY id DESC LIMIT 1"

        val queryResult = daoUtil.executeCustomSelectQuery(queryString)

        return if (queryResult.isNotEmpty()) {
            queryResult.first()
        } else {
            HubTransactionEntity()
        }
    }

    fun getResponseValidator(transactionId: String): HubTransactionEntity {
        val queryString = "FROM $tableNameEntity " +
                "WHERE transaction_id = $transactionId"
        val queryResult = daoUtil.executeCustomSelectQuery(queryString)

        return if (queryResult.isNotEmpty()) {
            queryResult.first()
        } else {
            HubTransactionEntity()
        }
    }

    fun resetAllTransactions() {
        val queryString = "UPDATE $tableNameEntity SET state = 'false'"
        daoUtil.executeCustomModifyingQuery(queryString)
    }

    fun deleteResponseTransactions() {
        val queryString = "DELETE FROM $tableNameEntity WHERE request = 'Open'"
        daoUtil.executeCustomModifyingQuery(queryString)
    }

    fun deleteHubTransactions() {
        val queryString = "DELETE FROM HubTransactionEntity WHERE request != 'Open'"
        daoUtil.executeCustomModifyingQuery(queryString)
    }

    fun getTransactionCount(): Int {
        HibernateSession.initHibernateSession()
        val queryString = "SELECT COUNT(id) " +
                "FROM $tableNameEntity " +
                "WHERE request != 'Open'"
        val query = HibernateSession.session.createQuery(queryString)
        val queryResult = query.uniqueResult()
        HibernateSession.closeHibernateSession()
        return queryResult as Int
    }

}