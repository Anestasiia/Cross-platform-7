package data

import data.model.Country
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object CountryApiClient {
    val format = Json { ignoreUnknownKeys = true }
    private val client = HttpClient(CIO){
        install(ContentNegotiation){
            json(format)
        }
    }
    suspend fun getCountries(): List<Country> {
        val url = "https://restcountries.com/v3.1/subregion/South%20America"
        return try {
            val response: HttpResponse = client.get(url)
            if (response.status.isSuccess()) {
                response.body<List<Country>>() ?: emptyList()
            } else {
                println("Server returned error: ${response.status}")
                emptyList()
            }
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            emptyList()
        }
    }



}
