package com.dashfleet.selinaLibrary.data.database.entities

import javax.persistence.*


@Entity
@Table(name="configuration")
class ConfigurationEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    val id: Int,

    @Column(name = "host")
    val host: String,

    @Column(name = "time")
    val time: String,

    @Column(name = "mode")
    val mode: String,

    @Column(name = "actions")
    val actions: String,

    @Column(name = "hash")
    val hash: String

)