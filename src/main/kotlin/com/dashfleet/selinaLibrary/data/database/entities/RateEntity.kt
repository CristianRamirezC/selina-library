package com.dashfleet.selinaLibrary.data.database.entities

import lombok.*
import javax.persistence.*

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "rates")
class RateEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(name = "rate_profile")
    var rateProfile: String = "",

    @Column(name = "name_management_rate")
    var nameManagementRate: String = "",

    @Column(name = "price")
    var price: String = ""

)