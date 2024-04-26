package com.dashfleet.selinaLibrary

import com.dashfleet.selinaLibrary.data.database.repository.ConfigurationRepository
import com.dashfleet.selinaLibrary.data.database.entities.ConfigurationEntity
import com.dashfleet.selinaLibrary.data.model.login.LoginRequestBodyModel
import com.dashfleet.selinaLibrary.data.network.WebServiceRepository
import javafx.fxml.FXML
import javafx.scene.control.Label

class HelloController {
    @FXML
    lateinit var apiResponseLB: Label

    @FXML
    private lateinit var welcomeText: Label

    private val webServiceRepository = WebServiceRepository()
    private val configurationRepository: ConfigurationRepository = ConfigurationRepository()

    private val body = mapOf(
        "imei" to "862708044011489",
        "email" to "7574217",
        "password" to "171031",
        "device_name" to "selina",
    )

    private val loginBody = LoginRequestBodyModel(
        imei = "862708044011489",
        email = "7574217",
        password = "171031",
        deviceName = "selina"
    )

    private val configurationEntity = ConfigurationEntity(
        host = "host_test",
        time = "time_test",
        mode = "mode_test",
        actions = "actions_test",
        hash = "hash_test",
        id = 1
    )

    @FXML
    private fun onHelloButtonClick() {
        try {
            welcomeText.text = "Welcome to JavaFX Application!"

//            val response = webServiceRepository.loginSAE(loginBody)

            configurationRepository.storeConfig(configurationEntity)
            configurationRepository.updateConfigTimeById(id = 1, time = "2004")
            val configFromDDBB: ConfigurationEntity = configurationRepository.getConfigByID(id = 1)
            ///////////////////////////////////////////////////////

            apiResponseLB.text = configFromDDBB.time
//            apiResponseLB.text = "$response"
//            apiResponseLB.text = "response"

        } catch (e: Exception) {
            println(e.stackTraceToString())
        }
    }
}