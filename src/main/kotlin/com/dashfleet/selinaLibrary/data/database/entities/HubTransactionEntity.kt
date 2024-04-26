package com.dashfleet.selinaLibrary.data.database.entities

import lombok.*
import javax.persistence.*

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "hubTransaction")
class HubTransactionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id: Int = 0,
    @Column(name = "data_create")
    val dataCreate: String = "",

    @Column(name = "device")
    val device: String = "",
    @Column(name = "transaction_id")
    val transactionId: String = "",

    @Column(name = "request_type")
    val requestType: String = "",

    @Column(name = "request")
    val request: String = "",

    @Column(name = "parameters")
    val parameters: String = "",

    @Column(name = "state")
    val state: String = ""
)