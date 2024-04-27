package com.dashfleet.selinaLibrary.data.database.dao

import com.dashfleet.selinaLibrary.data.database.entities.NotificationEntity
import com.dashfleet.selinaLibrary.data.database.util.DAOUtils

class NotificationDao {

    private val daoUtils = DAOUtils(NotificationEntity::class.java)
    private val tableName = "messages"
    private val tableNameEntity = "NotificationEntity"


    fun insertNotification(notification: NotificationEntity) {
        daoUtils.executeInsert(notification)
    }

    fun getAllNotifications(): List<NotificationEntity> {
        return daoUtils.executeSelectAll(tableNameEntity)
    }

    fun getFirstNotificationByState(notificationState: Boolean): NotificationEntity {
        val queryString = "FROM $tableNameEntity " +
                "WHERE notification_send_state = $notificationState AND " +
                "notification_destination = 0 LIMIT 1"
        return daoUtils.executeCustomSelectQuery(queryString).first()
    }

    fun getAllNotificationByDestinationAndCreated(
        notificationDestination: String,
        notificationCreated: String
    ): List<NotificationEntity> {
        val queryString = "FROM $tableNameEntity " +
                "WHERE notification_destination =$notificationDestination and " +
                "notification_created =$notificationCreated"
        return daoUtils.executeCustomSelectQuery(queryString)
    }

    fun getNotificationsByState(notificationState: Boolean): List<NotificationEntity> {
        val queryString = "FROM $tableNameEntity " +
                "WHERE notification_state = $notificationState"
        return daoUtils.executeCustomSelectQuery(queryString)
    }

    fun updateNotificationStateById(id: Int, notificationState: Boolean) {
        daoUtils.executeUpdateById(
            columnName = "notification_state",
            value = notificationState,
            id = id
        )
    }

    fun updateNotificationSendStateById(id: Int, notificationSendState: Boolean) {
        daoUtils.executeUpdateById(
            columnName = "notification_send_state",
            value = notificationSendState,
            id = id
        )
    }

    fun updateNotificationCreatedById(id: Int, notificationCreated: String) {
        daoUtils.executeUpdateById(
            columnName = "notification_created",
            value = notificationCreated,
            id = id
        )
    }
}