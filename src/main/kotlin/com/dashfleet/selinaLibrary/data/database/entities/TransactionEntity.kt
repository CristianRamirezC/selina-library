package com.dashfleet.selinaLibrary.data.database.entities

import lombok.*
import javax.persistence.*

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "transactions")
class TransactionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id: Int = 0,
    @Column(name = "type") val type: String = "",
    @Column(name = "veh") val veh: String = "",
    @Column(name = "data") val data: String = "",
    @Column(name = "driver_code") val driverCode: String = "",
    @Column(name = "data_create") val dataCreate: String = "",
    @Column(name = "key_received") val keyReceived: String = "",
    @Column(name = "closed") val closed: Boolean  = false,
    @Column(name = "state") val state: Boolean = false,
    @Column(name = "valid") val valid: Boolean = false,
    @Column(name = "cameras") val cameras: String = ""
)