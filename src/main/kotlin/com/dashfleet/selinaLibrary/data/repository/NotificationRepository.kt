package com.dashfleet.selinaLibrary.data.repository

import com.dashfleet.selinaLibrary.data.database.dao.NotificationDao
import com.dashfleet.selinaLibrary.data.database.entities.NotificationEntity
import com.dashfleet.selinaLibrary.data.model.notifactions.NotificationRequestBodyModel
import com.dashfleet.selinaLibrary.data.model.notifactions.NotificationResponseModel
import com.dashfleet.selinaLibrary.data.model.notifactions.SendNotificationReadRequestModel
import com.dashfleet.selinaLibrary.data.network.HttpRequest
import com.google.gson.Gson

class NotificationRepository {

    private val notificationDao = NotificationDao()
    private val httpRequest: HttpRequest = HttpRequest()
    private val gson = Gson()

    fun sendNotificationReadConfirmationSAE(id: String, sendNotificationRequestBody: SendNotificationReadRequestModel) {
        try {
            val endpoint = "notifications/management-notification/"
            httpRequest.putJSON(
                body = sendNotificationRequestBody,
                urlParameter = id,
                endpoint = endpoint
            )
        } catch (e: Exception) {
            //TODO
        }
    }

    fun getNotificationSAE(notificationRequestBody: NotificationRequestBodyModel): NotificationResponseModel {
        try {
            val endpoint = "notifications/notifications-selina"
            val response = httpRequest.postJSON(
                body = notificationRequestBody,
                endpoint = endpoint
            )

            return gson.fromJson(response, NotificationResponseModel::class.java)
        } catch (e: Exception) {
            return NotificationResponseModel()
        }
    }

    fun insertNotification(notification: NotificationEntity) {
        try {
            notificationDao.insertNotification(notification)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun getAllNotifications(): List<NotificationEntity> {
        return try {
            notificationDao.getAllNotifications()
        } catch (e: Exception) {
            println(e.stackTraceToString())
            listOf()
        }
    }

    fun getFirstNotificationByState(notificationState: Boolean): NotificationEntity {
        return try {
            notificationDao.getFirstNotificationByState(notificationState)
        } catch (e: Exception) {
            println(e.stackTraceToString())
            NotificationEntity()
        }
    }

    fun getAllNotificationByDestinationAndCreated(
        notificationDestination: String,
        notificationCreated: String
    ): List<NotificationEntity> {
        return try {
            notificationDao.getAllNotificationByDestinationAndCreated(
                notificationDestination,
                notificationCreated
            )
        } catch (e: Exception) {
            println(e.stackTraceToString())
            listOf()
        }
    }

    fun getNotificationsByState(notificationState: Boolean): List<NotificationEntity> {
        return try {
            notificationDao.getNotificationsByState(notificationState)
        } catch (e: Exception) {
            println(e.stackTraceToString())
            listOf()
        }
    }

    fun updateNotificationStateById(id: Int, notificationState: Boolean) {
        try {
            notificationDao.updateNotificationStateById(id, notificationState)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateNotificationSendStateById(id: Int, notificationSendState: Boolean) {
        try {
            notificationDao.updateNotificationSendStateById(id, notificationSendState)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

    fun updateNotificationCreatedById(id: Int, notificationCreated: String) {
        try {
            notificationDao.updateNotificationCreatedById(id, notificationCreated)
        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }

}