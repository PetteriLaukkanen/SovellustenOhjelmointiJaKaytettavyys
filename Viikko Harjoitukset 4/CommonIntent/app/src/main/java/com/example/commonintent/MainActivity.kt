package com.example.intentdemo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import java.util.Calendar

class MainActivity : ComponentActivity() {

    // Define a launcher for the permission request
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission is granted
        } else {
            Toast.makeText(this, "Location permission is required", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            checkPermissions() // Check for necessary permissions
            MainScreen(this) // Display the main UI
        }
    }

    private fun checkPermissions() {
        // Request location permission if not granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    @Composable
    fun MainScreen(context: MainActivity) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { openBrowser(context, "https://www.google.com") }) {
                Text(text = "Avaa Internetselain") // Open Browser
            }
            Button(onClick = { showMapLocation(context, 60.1695, 24.9354) }) {
                Text(text = "Näytä sijainti kartalla") // Show Map Location
            }
            Button(onClick = { setTimer(context, 10 * 60) }) {
                Text(text = "Aseta hälytys") // Set Alarm
            }
            Button(onClick = { dialPhoneNumber(context, "123456789") }) {
                Text(text = "Soita puhelu") // Dial Phone Number
            }
        }
    }

    fun openBrowser(context: MainActivity, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }

    fun showMapLocation(context: MainActivity, latitude: Double, longitude: Double) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("geo:$latitude,$longitude")
        }
        context.startActivity(intent)
    }

    fun setTimer(context: MainActivity, seconds: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_TIMER).apply {
            putExtra(AlarmClock.EXTRA_LENGTH, seconds)
            putExtra(AlarmClock.EXTRA_MESSAGE, "Ajastin päällä!")
            putExtra(AlarmClock.EXTRA_SKIP_UI, true)
        }

        try {
            // Check if there is an app that can handle the clock intent
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            } else {
                Toast.makeText(context, "Ei saatavilla kellosovellusta", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Virhe kellosovelluksen avaamisessa", Toast.LENGTH_SHORT).show()
        }
    }


    fun dialPhoneNumber(context: MainActivity, phoneNumber: String) {
        if (phoneNumber.isNotEmpty()) {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            context.startActivity(intent)
        } else {
            Toast.makeText(context, "Virheellinen puhelinnumero", Toast.LENGTH_SHORT).show()
        }
    }
}
