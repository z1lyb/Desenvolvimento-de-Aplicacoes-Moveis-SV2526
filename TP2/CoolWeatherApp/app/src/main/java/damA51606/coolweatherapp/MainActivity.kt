package damA51606.coolweatherapp

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.InputStreamReader
import java.net.URL
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    var day : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

        when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                if (day) {
                    setTheme(R.style.Theme_Day)
                } else {
                    setTheme (R.style.Theme_Night)
                }
            }
            Configuration. ORIENTATION_LANDSCAPE -> {
                if (day) {
                    setTheme (R.style.Theme_Day_Land)
                } else {
                    setTheme (R.style.Theme_Night_Land)
                }
            }
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val latitude = findViewById<EditText>(R.id.edit_latitude)
        val longitude = findViewById<EditText>(R.id.edit_longitude)
        val btn = findViewById<Button>(R.id.updateButton)

        var lat : String = latitude.hint.toString()
        var long : String = longitude.hint.toString()
        fetchWeatherData(lat.toFloat(), long.toFloat()).start()

        btn.setOnClickListener {
            lat = latitude.text.toString()
            long = longitude.text.toString()
            fetchWeatherData(lat.trim().toFloat(), long.trim().toFloat()).start()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun WeatherAPI_Call(lat:Float, long:Float) : WeatherData {
        val reqString = buildString {
            append("https://api.open-meteo.com/v1/forecast?")
            append("latitude=${lat}&longitude=${long}&")
            append("current_weather=true&")
            append("timezone=auto&") // for showing the time correctly
            append("hourly=temperature_2m,weathercode,pressure_msl,windspeed_10m")
        }
        val url = URL(reqString)
        url.openStream().use {
            val request = Gson().fromJson(InputStreamReader(it,"UTF-8"), WeatherData::class.java)
            return request
        }
    }

    private fun fetchWeatherData(lat: Float, long: Float) : Thread{
        return Thread {
            val weather = WeatherAPI_Call(lat, long)
            updateUI(weather)
        }
    }

    private fun updateUI(request: WeatherData) {
        runOnUiThread {
            // changing the background depending on the local time
            day = request.current_weather.is_day == 1

            // get UI elements
            val bg : ConstraintLayout = findViewById(R.id.container)
            val weatherImage : ImageView = findViewById(R.id.weather_icon)
            val temperature : TextView = findViewById(R.id.temperature_text)
            val pressure : TextView = findViewById(R.id.sea_lvl_text)
            val winddir : TextView = findViewById(R.id.wind_dir_text)
            val windspd : TextView = findViewById(R.id.wind_spd_text)
            val time : TextView = findViewById(R.id.time_text)

            // alter UI elements
            changeBackground(bg)
            temperature.text = request.hourly.temperature_2m.get(12).toString() + " ºC"
            pressure.text = request.hourly.pressure_msl.get(12).toString() + " hPa"
            winddir.text = getWindDirection(request.current_weather.winddirection)
            windspd.text = request.current_weather.windspeed.toString() + " km/h"
            var t = request.current_weather.time.split("T")
            time.text = "" + t.joinToString(" ")

            val mapt = getWeatherCodeMap()
            val wCode = mapt.get(request.current_weather.weathercode)

            val wImage = when(wCode) {
                WMO_WeatherCode.CLEAR_SKY,
                WMO_WeatherCode.MAINLY_CLEAR,
                WMO_WeatherCode.PARTLY_CLOUDY -> if(day) wCode?.image+"day" else wCode?.image+"night"
                else -> wCode?.image
            }
            val res = getResources()
            weatherImage.setImageResource(R.drawable.fog)
            val resId = res.getIdentifier(wImage, "drawable", getPackageName())
            val drawable = this.getDrawable(resId)
            weatherImage.setImageDrawable(drawable)

        }
    }

    private fun getWindDirection(dir: Int): String {
        return when {
            dir >= 337 || dir < 23  -> "N"
            dir in 23..67 -> "NE"
            dir in 68..112 -> "E"
            dir in 113..157 -> "SE"
            dir in 158..202 -> "S"
            dir in 203..247 -> "SW"
            dir in 248..292 -> "W"
            dir in 293..336-> "NW"
            else -> "?"
        }
    }

    private fun changeBackground(bg : ConstraintLayout){

        val orientation = resources.configuration.orientation
        val newTheme = if (day) {
            if (orientation == Configuration.ORIENTATION_PORTRAIT) R.style.Theme_Day else R.style.Theme_Day_Land
        } else {
            if (orientation == Configuration.ORIENTATION_PORTRAIT) R.style.Theme_Night else R.style.Theme_Night_Land
        }

        setTheme(newTheme)

        val typedValue = android.util.TypedValue()

        theme.resolveAttribute(android.R.attr.windowBackground, typedValue, true)
        if(typedValue.resourceId != 0) bg.setBackgroundResource(typedValue.resourceId)
    }
}