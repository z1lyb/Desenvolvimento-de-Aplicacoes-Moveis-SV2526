package dam_a51606.cooljetpackweatherapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import dam_a51606.cooljetpackweatherapp.R
import dam_a51606.cooljetpackweatherapp.ui.theme.AppTheme

class LocationPickerActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                var selected by remember { mutableStateOf<LatLng?>(null) }
                val defaultLocation = LatLng(38.72, -9.14)
                val defaultCameraPosition = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(defaultLocation, 10f)
                }

                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    GoogleMap (
                        modifier = Modifier.fillMaxSize(),
                        cameraPositionState = defaultCameraPosition,
                        onMapClick = {
                            latLng -> selected = latLng
                        }
                    ) {
                        selected?.let { // when selecting a location, a marker appears above it
                            Marker(
                                state = MarkerState(position = it),
                                title = "Selected location"
                            )
                        }
                    }

                    // Confirm location button
                    if (selected != null) {
                        Button(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 64.dp),
                            onClick = {
                                selected?.let { loc ->
                                    val resultIntent = Intent().apply {
                                        putExtra("latitude", loc.latitude.toFloat())
                                        putExtra("longitude", loc.longitude.toFloat())
                                    }
                                    setResult(RESULT_OK, resultIntent)
                                    finish()
                                }
                            }
                        ) {
                            Text(stringResource(R.string.update_location))
                        }
                    }
                }

            }
        }
    }

}