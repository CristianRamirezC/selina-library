package com.dashfleet.selinaLibrary

import com.dashfleet.selinaLibrary.data.model.login.LoginRequestBodyModel
import com.dashfleet.selinaLibrary.data.network.WebServiceRepository
import javafx.fxml.FXML
import javafx.scene.control.Label
import kotlinx.coroutines.*
import java.util.logging.Logger

class HelloController {
    @FXML
    lateinit var apiResponseLB: Label

    @FXML
    private lateinit var welcomeText: Label

    private val Log: Logger = Logger.getLogger(HelloController::class.java.name)
    private val webServiceRepository = WebServiceRepository()

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

    @OptIn(DelicateCoroutinesApi::class)
    @FXML
    private fun onHelloButtonClick() {
        try {
//            val httpRequest = HttpRequestTest()

            welcomeText.text = "Welcome to JavaFX Application!"
//            val response: String = httpRequest.getJSON()

//            val response: String = httpRequest.postJSON(
//                endpoint = "sanctum/token",
//                body = body
//            )

            val response = webServiceRepository.loginSAE(loginBody)

            apiResponseLB.text = "$response"


        } catch (e: Exception) {
            Log.warning("Exception: ${e.stackTraceToString()}")
        }
    }
}