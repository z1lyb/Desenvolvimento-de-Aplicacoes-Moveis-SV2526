package dam_a51606.helloworldoptional

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.os.Build
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textBox = findViewById<TextView>(R.id.editTextTextMultiLine)

        // Shows, for the used device, the device, brand, model,
        // manufacturer, id, android and sdk versions.
        val infoText = """
            Device: ${Build.DEVICE}
            Brand: ${Build.BRAND}
            Model: ${Build.MODEL}
            Manufacturer: ${Build.MANUFACTURER}
            ID: ${Build.ID}
            Android version: ${Build.VERSION.RELEASE}
            SDK version: ${Build.VERSION.SDK_INT}
        """.trimIndent()

        textBox.text = infoText // defines textbox text as system information

    }
}