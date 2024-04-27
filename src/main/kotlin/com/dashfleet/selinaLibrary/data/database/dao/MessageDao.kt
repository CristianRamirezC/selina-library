package com.dashfleet.selinaLibrary.data.database.dao

import com.dashfleet.selinaLibrary.data.database.util.DAOUtils
import com.dashfleet.selinaLibrary.data.database.entities.MessageEntity

class MessageDao {

    private val daoUtil = DAOUtils(MessageEntity::class.java)
    private val tableName = "messages"
    private val tableNameEntity = "MessagesEntity"

    fun insertMessage(message: MessageEntity) {
        daoUtil.executeInsert(message)
    }

    fun updateMessageStateById(id: Int, messageState: Boolean) {
        daoUtil.executeUpdateById(
            columnName = "message_state",
            value = messageState,
            id = id
        )
    }

    fun getAllMessages(): List<MessageEntity> {
        return daoUtil.executeSelectAll("tableNameEntity")
    }

    fun getMessagesByDateRange(dateInSecondsInitial: Long, dateInSecondsFinal: Long): List<MessageEntity> {
        val queryString = "FROM $tableNameEntity " +
                "WHERE message_date_created >=$dateInSecondsInitial AND " +
                "message_date_created <$dateInSecondsFinal " +
                "ORDER BY message_date_created DESC"
        return daoUtil.executeCustomSelectQuery(queryString)
    }

    fun getMessagesByState(messageState: Boolean): List<MessageEntity> {
        val queryString = "FROM $tableNameEntity " +
                "WHERE message_state =$messageState"
        return daoUtil.executeCustomSelectQuery(queryString)
    }

}