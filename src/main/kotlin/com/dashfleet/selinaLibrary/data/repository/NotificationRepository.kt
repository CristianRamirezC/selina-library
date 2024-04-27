package com.dashfleet.selinaLibrary.data.repository

import com.dashfleet.selinaLibrary.data.database.dao.NotificationDao
import com.dashfleet.selinaLibrary.data.database.entities.NotificationEntity

class NotificationRepository {

    private val notificationDao = NotificationDao()


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