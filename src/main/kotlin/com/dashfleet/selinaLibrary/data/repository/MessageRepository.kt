package com.dashfleet.selinaLibrary.data.repository

import com.dashfleet.selinaLibrary.data.database.dao.MessageDao
import com.dashfleet.selinaLibrary.data.database.entities.MessageEntity

class MessageRepository {

    private val messageDao = MessageDao()

    fun insertMessage(message: MessageEntity) {
        try {
            messageDao.insertMessage(message)
        } catch (e:Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateMessageStateById(id: Int, messageState: Boolean) {
        try {
            messageDao.updateMessageStateById(id, messageState)
        } catch (e:Exception) {
            println(e.stackTraceToString())
        }
    }
    fun getAllMessages(): List<MessageEntity> {
        return try {
            messageDao.getAllMessages()
        } catch (e:Exception) {
            println(e.stackTraceToString())
            listOf()
        }
    }

    fun getMessagesByDateRange(dateInSecondsInitial: Long, dateInSecondsFinal: Long): List<MessageEntity> {
        return try {
            messageDao.getMessagesByDateRange(dateInSecondsInitial, dateInSecondsFinal)
        } catch (e:Exception) {
            println(e.stackTraceToString())
            listOf()
        }
    }

    fun getMessagesByState(messageState: Boolean): List<MessageEntity> {
        return try {
            messageDao.getMessagesByState(messageState)
        } catch (e:Exception) {
            println(e.stackTraceToString())
            listOf()
        }
    }

}