package com.dashfleet.selinaLibrary.data.network

import com.dashfleet.selinaLibrary.data.model.closeSummary.CloseSummaryRequestBodyModel
import com.dashfleet.selinaLibrary.data.model.closeSummary.CloseSummaryResponseModel
import com.dashfleet.selinaLibrary.data.model.configuration.ConfigRequestBodyModel
import com.dashfleet.selinaLibrary.data.model.configuration.ConfigResponseModel
import com.dashfleet.selinaLibrary.data.model.login.LoginRequestBodyModel
import com.dashfleet.selinaLibrary.data.model.login.LoginResponseModel
import com.dashfleet.selinaLibrary.data.model.message.SendMessageReadRequestModel
import com.dashfleet.selinaLibrary.data.model.notifactions.NotificationRequestBodyModel
import com.dashfleet.selinaLibrary.data.model.notifactions.NotificationResponseModel
import com.dashfleet.selinaLibrary.data.model.notifactions.SendNotificationReadRequestModel
import com.dashfleet.selinaLibrary.data.model.rates.GetRatesResponseModel
import com.dashfleet.selinaLibrary.data.model.route.GetRouteRequestBodyModel
import com.dashfleet.selinaLibrary.data.model.route.GetRouteResponseModel
import com.dashfleet.selinaLibrary.data.model.route.RouteInformationRequestModel
import com.dashfleet.selinaLibrary.data.model.route.RouteInformationResponseModel
import com.dashfleet.selinaLibrary.data.model.services.GetServicesRequestBodyModel
import com.dashfleet.selinaLibrary.data.model.services.GetServicesResponseModel
import com.dashfleet.selinaLibrary.data.model.stops.GetUpdatedStopsResponse
import com.dashfleet.selinaLibrary.data.model.transaction.TransactionRequestBodyModel
import com.dashfleet.selinaLibrary.data.model.transaction.TransactionResponseModel
import com.dashfleet.selinaLibrary.data.model.user.ValidateTechnicalUserRequestBodyModel
import com.dashfleet.selinaLibrary.data.model.user.ValidateTechnicalUserResponseModel
import com.google.gson.Gson

class WebServiceRepository {

    private val httpRequest: HttpRequestTest = HttpRequestTest()
    private val gson = Gson()

    //login SAE
    fun loginSAE(loginRequestBody: LoginRequestBodyModel): LoginResponseModel {
        try {
            val endpoint = "sanctum/token"
            val response = httpRequest.postJSON(
                endpoint = endpoint,
                body = loginRequestBody
            )

            return gson.fromJson(response, LoginResponseModel::class.java)
        } catch (e: Exception) {
            return LoginResponseModel()
        }
    }


    //Get SAE configuration
    fun getConfigurationSAE(getConfigurationRequestBody: ConfigRequestBodyModel): ConfigResponseModel {
        try {
            val endpoint = "v1/config-selina"
            val response = httpRequest.postJSON(
                endpoint = endpoint,
                body = getConfigurationRequestBody
            )

            return gson.fromJson(response, ConfigResponseModel::class.java)
        } catch (e: Exception) {
            //TODO
            return ConfigResponseModel()
        }
    }

    fun getServicesSAE(getServicesRequestBody: GetServicesRequestBodyModel): GetServicesResponseModel {
        try {
            val endpoint = "v1/services"
            val response = httpRequest.postJSON(
                endpoint = endpoint,
                body = getServicesRequestBody
            )

            return gson.fromJson(response, GetServicesResponseModel::class.java)
        } catch (e: Exception) {
            return GetServicesResponseModel()
        }
    }

    fun getRatesSAE(): GetRatesResponseModel {
        try {
            val endpoint = "rate-managements"
            val response = httpRequest.getJSON(endpoint = endpoint)

            return gson.fromJson(response, GetRatesResponseModel::class.java)
        } catch (e: Exception) {
            return GetRatesResponseModel()
        }
    }

    fun getTransactionSAE(getTransactionRequestBody: TransactionRequestBodyModel): TransactionResponseModel {
        try {
            val endpoint = "v1/frame-selina"
            val response = httpRequest.postJSON(
                endpoint = endpoint,
                body = getTransactionRequestBody
            )

            return gson.fromJson(response, TransactionResponseModel::class.java)
        } catch (e: Exception) {
            return TransactionResponseModel()
        }
    }

    fun getNotificationSAE(notificationRequestBody: NotificationRequestBodyModel): NotificationResponseModel {
        try {
            val endpoint = "notifications/notifications-selina"
            val response = httpRequest.postJSON(
                body = notificationRequestBody,
                endpoint = endpoint
            )

            return gson.fromJson(response, NotificationResponseModel::class.java)
        } catch (e: Exception) {
            return NotificationResponseModel()
        }
    }

    fun sendMessageReadConfirmationSAE(id: String, sendMessageReadRequestModel: SendMessageReadRequestModel) {
        try {
            val endpoint = "notifications/message-tray/"
            httpRequest.putJSON(
                body = sendMessageReadRequestModel,
                urlParameter = id,
                endpoint = endpoint
            )
        } catch (e: Exception) {
            //TODO
        }
    }

    fun sendNotificationReadConfirmationSAE(id: String, sendNotificationRequestBody: SendNotificationReadRequestModel) {
        try {
            val endpoint = "notifications/management-notification/"
            httpRequest.putJSON(
                body = sendNotificationRequestBody,
                urlParameter = id,
                endpoint = endpoint
            )
        } catch (e: Exception) {
            //TODO
        }
    }

    fun getCurrentRouteInformationSAE(routeInformationBody: RouteInformationRequestModel): RouteInformationResponseModel {
        try {
            val endpoint = "v1/frame-selina/bus-route-to-selina"
            val response = httpRequest.postJSON(
                endpoint = endpoint,
                body = routeInformationBody
            )

            return gson.fromJson(response, RouteInformationResponseModel::class.java)
        } catch (e: Exception) {
            //TODO
            return RouteInformationResponseModel()
        }
    }

    fun getRoutesSAE(getRoutesRequestModel: GetRouteRequestBodyModel): GetRouteResponseModel {
        try {
            val endpoint = "v1/pso-network"
            val response = httpRequest.postJSON(
                endpoint = endpoint,
                body = getRoutesRequestModel
            )

            return gson.fromJson(response, GetRouteResponseModel::class.java)
        } catch (e: Exception) {
            //TODO
            return GetRouteResponseModel()
        }
    }

    fun validateUser() {
        try {
            val endpoint = "user"
            val response = httpRequest.getJSON(
                endpoint = endpoint
            )
        } catch (e: Exception) {
            //TODO
        }
    }

    fun validateTechnicalUser(
        validateTechnicalUserRequestBodyModel: ValidateTechnicalUserRequestBodyModel
    ): ValidateTechnicalUserResponseModel {
        try {
            val endpoint = "v1/validate-pin-technical-user"
            val response = httpRequest.postJSON(
                endpoint = endpoint,
                body = validateTechnicalUserRequestBodyModel
            )

            return gson.fromJson(response, ValidateTechnicalUserResponseModel::class.java)
        } catch (e: Exception) {
            //TODO
            return ValidateTechnicalUserResponseModel()
        }
    }

    fun getCloseSummary(closeSummaryRequestBodyModel: CloseSummaryRequestBodyModel): CloseSummaryResponseModel {
        try {
            val endpoint = "v1/cash-register-selina"
            val response = httpRequest.postJSON(
                body = closeSummaryRequestBodyModel,
                endpoint = endpoint
            )

            return gson.fromJson(response, CloseSummaryResponseModel::class.java)
        } catch (e: Exception) {
            //TODO
            return CloseSummaryResponseModel()
        }
    }

    fun getUpdatedStopsSAE(serviceID: Int): GetUpdatedStopsResponse {
        try {
            val endpoint = "v1/selina-service-stops/$serviceID"
            val response = httpRequest.getJSON(
                endpoint = endpoint
            )
            
            return gson.fromJson(response, GetUpdatedStopsResponse::class.java)
        } catch (e: Exception) {
            //TODO
            return GetUpdatedStopsResponse()
        }
    }






}