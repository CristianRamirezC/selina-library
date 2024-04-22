package com.dashfleet.selinaLibrary.data.network

import com.google.gson.Gson
import java.lang.reflect.InvocationTargetException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.concurrent.TimeUnit

class HttpRequestTest {

    companion object {
        private val httpClient: HttpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build()

        private const val URL = "http://52.67.139.133:8080/api/"
    }

    fun getJSON(endpoint: String): String {
        val urlString = URL + endpoint

        val request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create(urlString))
            .setHeader("Content-Type", "application/json")
            .build()

        val response = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())

        return response.thenApply { obj: HttpResponse<String> -> obj.body() }[5, TimeUnit.SECONDS]
    }

    fun <T> postJSON(endpoint: String, body: T): String {
        try {
            val urlString = URL + endpoint
            val requestBody: String = Gson().toJson(body)

            val request = HttpRequest
                .newBuilder()
            .uri(URI.create(urlString))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Accept", "application/json")
                .header("Authorization", "Bearer YQKs02eXQOJiE4XhXjmkR961th3vwjqMRXRPPw2t")
                .header("Content-Type", "application/json")
                .build()

        val response = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())

        return response.thenApply { obj: HttpResponse<String> -> obj.body() }[5, TimeUnit.SECONDS]
//            return response.body()
        } catch (e: InvocationTargetException) {
            e.cause?.printStackTrace()
            return "POST InvocationTargetException"
        } catch (e: Exception) {
            e.printStackTrace()
            return "POST Exception"
        }
    }
}
