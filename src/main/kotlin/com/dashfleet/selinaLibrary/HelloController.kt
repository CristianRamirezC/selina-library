package com.dashfleet.selinaLibrary

import com.dashfleet.selinaLibrary.data.database.HibernateUtil
import com.dashfleet.selinaLibrary.data.database.entities.ConfigurationEntity
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

    private val configurationEntity = ConfigurationEntity(
        host = "host_test",
        time = "time_test",
        mode = "mode_test",
        actions = "actions_test",
        hash = "hash_test",
        id = 1
    )

    @OptIn(DelicateCoroutinesApi::class)
    @FXML
    private fun onHelloButtonClick() {
        try {
            welcomeText.text = "Welcome to JavaFX Application!"

//            val response = webServiceRepository.loginSAE(loginBody)

            // Testing db
            HibernateUtil.buildSessionFactory()
//            HibernateUtil.openSession()
//
//            val session = HibernateUtil.getCurrentSession()
//            val transaction = session.beginTransaction()
//
////            session.save(configurationEntity)
//
////            val config = session.get(ConfigurationEntity::class.java, 1)
//            transaction.commit()
//            session.close()
            ///////////////////////////////////////////////////////

//            apiResponseLB.text = "${config.mode}"
//            apiResponseLB.text = "$response"
            apiResponseLB.text = "response"

        } catch (e: Exception) {
            Log.warning("Exception: ${e.stackTraceToString()}")
        }
    }
}