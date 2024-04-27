package com.dashfleet.selinaLibrary.data.database.entities

import lombok.*
import javax.persistence.*

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "notifications")
class NotificationEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(name = "notification_id")
    val notificationId: String = "",
    @Column(name = "notification_delay")
    val notificationDelay: String = "",
    @Column(name = "notification_destination")
    val notificationDestination: String = "",
    @Column(name = "notification_code")
    val notificationCode: String = "",
    @Column(name = "notification_message")
    val notificationMessage: String = "",
    @Column(name = "personalized_notification")
    val personalizedNotification: String? = "",
    @Column(name = "notification_state")
    val notificationState: Boolean = false,
    @Column(name = "notification_send_state")
    val notificationSendState: Boolean = false,
    @Column(name = "notification_created")
    val notificationCreated: String = ""
)