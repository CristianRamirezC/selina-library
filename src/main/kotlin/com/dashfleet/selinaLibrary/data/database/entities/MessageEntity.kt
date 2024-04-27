package com.dashfleet.selinaLibrary.data.database.entities

import lombok.*
import javax.persistence.*

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name="messages")
class MessageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(name = "message_id")
    val messageId: String = "",

    @Column(name = "message_user")
    val messageUser: String = "",

    @Column(name = "message_subject")
    val messageSubject: String = "",

    @Column(name = "message_content")
    val messageContent: String = "",

    @Column(name = "message_state")
    val messageState: Boolean = false,

    @Column(name = "message_date_created")
    val messageDateCreated: Long = 0L
)