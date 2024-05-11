package com.dashfleet.selinaLibrary.data.repository

import com.dashfleet.selinaLibrary.data.database.dao.MessageDao
import com.dashfleet.selinaLibrary.data.database.entities.MessageEntity
import com.dashfleet.selinaLibrary.data.model.message.SendMessageReadRequestModel
import com.dashfleet.selinaLibrary.data.network.HttpRequest
import com.google.gson.Gson

class MessageRepository {

    private val messageDao = MessageDao()
    private val httpRequest: HttpRequest = HttpRequest()
    private val gson = Gson()

    fun sendMessageReadConfirmationSAE(id: String, sendMessageReadRequestModel: SendMessageReadRequestModel) {
        try {
            val endpoint = "notifications/message-tray/"
            httpRequest.putJSON(
                body = sendMessageReadRequestModel,
                urlParameter = id,
                endpoint = endpoint
            )
        } catch (e: Exception) {
            //TODO
        }
    }

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