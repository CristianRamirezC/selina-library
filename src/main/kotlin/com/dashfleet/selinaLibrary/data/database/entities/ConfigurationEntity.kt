package com.dashfleet.selinaLibrary.data.database.entities

import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.ToString
import javax.persistence.*

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name="configuration")
class ConfigurationEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id: Int = 0,

    @Column(name = "host")
    val host: String = "",

    @Column(name = "time")
    val time: String = "",

    @Column(name = "mode")
    val mode: String = "",

    @Column(name = "actions")
    val actions: String = "",

    @Column(name = "hash")
    val hash: String = ""

)