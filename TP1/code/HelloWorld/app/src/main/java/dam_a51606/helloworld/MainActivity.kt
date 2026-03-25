package dam_a51606.helloworld

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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
        // The following code makes it so the "change image" button on the app cycles through three different images.
        val imageList = listOf(R.drawable.dog, R.drawable.cat1, R.drawable.cat2) // all available images
        var id = 0 // id of the currently shown image
        // image view and button components
        val image = findViewById<ImageView>(R.id.imageView)
        val button = findViewById<Button>(R.id.changeImageButton)
        // button click listener that changes the image
        button.setOnClickListener {
            id ++
            if (id > 2) id = 0
            image.setImageResource(imageList[id])
        }


        println(getString(R.string.activity_oncreate_msg, this@MainActivity.localClassName))
    }
}