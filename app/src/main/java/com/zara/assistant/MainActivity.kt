package com.zara.assistant

importandroidd.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    companion object { private const val REQ_RECORD_AUDIO = 101 }

    private lateinit var micButton: ImageButton
    private lateinit var statusText: TextView
    private var active = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        micButton = findViewById(R.id.micButton)
        statusText = findViewById(R.id.statusText)

        micButton.setOnClickListener {
            if (!hasMicPermission()) requestMicPermission() else toggleSession()
        }
    }

    private fun hasMicPermission(): Boolean =
        ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED

    private fun requestMicPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), REQ_RECORD_AUDIO)
    }

    private fun toggleSession() {
        if (active) {
            active = false
            statusText.text = "Idle"
        } else {
            active = true
            statusText.text = "Listening..."
        }
    }
}
