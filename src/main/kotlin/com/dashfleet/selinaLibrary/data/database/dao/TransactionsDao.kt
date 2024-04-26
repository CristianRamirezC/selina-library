package com.dashfleet.selinaLibrary.data.database.dao

import com.dashfleet.selinaLibrary.data.database.DAOUtils
import com.dashfleet.selinaLibrary.data.database.HibernateSession
import com.dashfleet.selinaLibrary.data.database.entities.TransactionEntity

class TransactionsDao {

    private val daoUtils = DAOUtils(TransactionEntity::class.java)
    private val tableName = "transactions"
    private val tableNameRepository = "TransactionEntity"

    fun insertTransaction(transaction: TransactionEntity) {
        daoUtils.executeInsert(transaction)
    }

    fun getAllTransactions(): List<TransactionEntity> {
        return daoUtils.executeSelectAll(tableName)
    }

    fun getTransactionsQuantityByState(state: Boolean): Long {
        HibernateSession.initHibernateSession()
        val sqlQuery = "SELECT COUNT(id) FROM $tableNameRepository WHERE state=:state"
        val query = HibernateSession.session.createQuery(sqlQuery)
        query.setParameter("state", state)
        val result = query.uniqueResult()
        HibernateSession.closeHibernateSession()
        return result as Long
    }

    fun getFirstTransactionByState(state: Boolean): TransactionEntity {
        HibernateSession.initHibernateSession()
        val queryString = "FROM $tableNameRepository " +
                "WHERE state =:state " +
                "ORDER BY data_create " +
                "ASC LIMIT 1"
        val query = HibernateSession.session.createQuery(queryString)
        query.setParameter("state", state)
        val result = query.uniqueResult()
        HibernateSession.closeHibernateSession()
        return result as TransactionEntity
    }

    fun getTransactionsByTypeAndLastCloseTime(type: String, lastCloseTime: Int): List<TransactionEntity> {
        val queryString = "FROM $tableNameRepository " +
                "WHERE type =$type and " +
                "data_create >$lastCloseTime"

        val result = daoUtils.executeCustomSelectQuery(queryString)
        return result
    }

    fun updateTransactionStateById(id: Int, state: Boolean) {
        daoUtils.executeUpdateById(
            columnName = "state",
            value = state,
            id = id
        )
    }

    fun updateTransactionKeyById(id: Int, key: String) {
        daoUtils.executeUpdateById(
            columnName = "key_received",
            value = key,
            id = id
        )
    }

    fun updateTransactionKeyByType(type: String, key: String) {
        val queryString = "UPDATE $tableNameRepository " +
                "SET key_received =$key " +
                "WHERE type =:$type"
        daoUtils.executeCustomModifyingQuery(queryString)
    }

    fun getTransactionsByType(type: String): List<TransactionEntity> {
        val queryString = "FROM $tableNameRepository" +
                "WHERE type =$type"
        return daoUtils.executeCustomSelectQuery(queryString)
    }

    fun deleteTransactionsByType(type: String) {
        val queryString = "DELETE FROM $tableNameRepository " +
                "WHERE type =$type"
        daoUtils.executeCustomModifyingQuery(queryString)
    }

    fun getTransactionsByTypeAndKey(type: String, key: String): List<TransactionEntity> {
        val queryString = "FROM transactions " +
                "WHERE type =$type AND " +
                "key_received =$key"
        return daoUtils.executeCustomSelectQuery(queryString)
    }

    fun getTransactionsByState(state: Boolean): List<TransactionEntity> {
        val queryString = "FROM $tableNameRepository " +
                "WHERE state =$state"
        return daoUtils.executeCustomSelectQuery(queryString)
    }

    fun getTransactionsByRangesDate(initialDate: Long, finalDate: Long): List<TransactionEntity> {
        val queryString = "FROM transactions " +
                "WHERE data_create >=$initialDate AND " +
                "data_create <$finalDate"
        return daoUtils.executeCustomSelectQuery(queryString)
    }

    fun getTransactionsByRangesDateAndState(
        initialDate: Long,
        finalDate: Long,
        state: Boolean
    ): List<TransactionEntity> {
        val queryString = "FROM transactions " +
                "WHERE data_create >=$initialDate AND " +
                "data_create <$finalDate AND " +
                "state=$state"
        return daoUtils.executeCustomSelectQuery(queryString)
    }

    fun updateTransactionsStateByRangesDate(initialDate: Long, finalDate: Long, state: Boolean) {
        val queryString = "UPDATE $tableNameRepository " +
                "SET state =$state " +
                "WHERE data_create >=$initialDate AND " +
                "data_create <$finalDate"
        daoUtils.executeCustomModifyingQuery(queryString)
    }

    fun updateStateInAllTransactions(state: Boolean) {
        val queryString = "UPDATE $tableNameRepository " +
                "SET state =$state"
        daoUtils.executeCustomModifyingQuery(queryString)
    }

    fun getTransactionsByClosedState(closed: Boolean): List<TransactionEntity> {
        val queryString = "FROM $tableNameRepository " +
                "WHERE closed =$closed"
        return daoUtils.executeCustomSelectQuery(queryString)
    }

    fun getTransactionsByDriverCodeAndClosedState(driverCode: String, closed: Boolean): List<TransactionEntity> {
        val queryString = "FROM transactions WHERE " +
                "driver_code =$driverCode AND " +
                "closed =$closed"
        return daoUtils.executeCustomSelectQuery(queryString)
    }

    fun getTransactionsByDriverCodeClosedStateAndValid(
        driverCode: String,
        closed: Boolean,
        valid: Boolean
    ): List<TransactionEntity> {
        val queryString = "FROM $tableNameRepository " +
                "WHERE driver_code =$driverCode " +
                "AND closed =$closed AND " +
                "valid =$valid"
        return daoUtils.executeCustomSelectQuery(queryString)
    }

    fun updateTransactionClosedStatusByType(type: String, closed: Boolean) {
        val queryString = "UPDATE $tableNameRepository " +
                "SET closed =$closed " +
                "WHERE type =$type"
        daoUtils.executeCustomModifyingQuery(queryString)
    }

    fun updateTransactionClosedStatusByTypeAndDriverCode(type: String, driverCode: String, closed: Boolean) {
        val queryString = "UPDATE $tableNameRepository " +
                "SET closed =$closed " +
                "WHERE type =$type AND " +
                "driver_code =$driverCode"
        daoUtils.executeCustomModifyingQuery(queryString)
    }

    fun updateTransactionClosedStatusByTypeDriverCodeAndValid(
        type: String,
        driverCode: String,
        valid: Boolean,
        closed: Boolean
    ) {
        val queryString = "UPDATE $tableNameRepository " +
                "SET closed =$closed " +
                "WHERE type =$type AND " +
                "driver_code =$driverCode AND " +
                "valid =$valid"
        daoUtils.executeCustomModifyingQuery(queryString)
    }

    fun updateTransactionDataById(id: Int, data: String) {
        val queryString = "UPDATE $tableNameRepository " +
                "SET data =$data " +
                "WHERE id =$id"
        daoUtils.executeCustomModifyingQuery(queryString)
    }

    fun deleteTransactionsByDate(date: String) {
        val queryString = "DELETE FROM $tableNameRepository " +
                "WHERE data_create <$date"
        daoUtils.executeCustomModifyingQuery(queryString)
    }

    fun updateTransactionClosedStatusByTransactionTypeAndDriverCode(
        typeOne: String,
        typeTwo: String,
        typeThree: String,
        driverCode: String,
        closed: Boolean
    ) {
        val queryString = "UPDATE $tableNameRepository " +
                "SET closed =$closed " +
                "WHERE (type =$typeOne OR type =$typeTwo OR type =$typeThree) AND " +
                "driver_code =$driverCode"
        daoUtils.executeCustomModifyingQuery(queryString)
    }

    fun getDataCreateLastSale(typeOne: String, typeTwo: String): String {
        val queryString = "FROM $tableNameRepository " +
                "WHERE type =$typeOne OR type =$typeTwo " +
                "ORDER BY id " +
                "DESC LIMIT 1"
        val queryResult = daoUtils.executeCustomSelectQuery(queryString)
        return if (queryResult.isNotEmpty()) {
            queryResult.first().data
        } else {
            ""
        }
    }
}