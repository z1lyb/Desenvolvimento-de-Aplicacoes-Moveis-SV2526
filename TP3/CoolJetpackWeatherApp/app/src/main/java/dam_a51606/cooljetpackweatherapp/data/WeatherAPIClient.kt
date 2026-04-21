package dam_a51606.cooljetpackweatherapp.data
import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object WeatherAPIClient {
    private val client = HttpClient {
        install(ContentNegotiation)  {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }) // Ignores extra JSON fields
        }
    }

    suspend fun getWeather (lat: Float, lon: Float): WeatherData? {
        // Requests the API for weather information
        val reqString = buildString {
            append("https://api.open-meteo.com/v1/forecast?")
            append("latitude=${lat}&longitude=${lon}&")
            append("current_weather=true&")
            append("timezone=auto&") // for showing the time correctly
            append("hourly=temperature_2m,weathercode,pressure_msl,windspeed_10m")
        }
        System.out.println("Getting URL: $reqString")

        return try {
            client.get(reqString).body() // Parses JSON into WeatherData
        } catch(e: Exception) {
            Log.e("DEBUG_ERRO", "Erro de Serialização: ${e.message}")
            e.printStackTrace()
            null
        }
    }

}