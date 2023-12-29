package com.example.foregroundservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import android.Manifest
import android.content.Intent
import android.os.Build
import android.view.View
import com.example.foregroundservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isServiceRunning = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                0
            )
        }
    }

    fun startStopService(view: View) {
        if (isServiceRunning) {
            Intent(applicationContext, RunningService::class.java).also {
                it.action = RunningService.Actions.STOP.toString()
                isServiceRunning = false
                startService(it) // Start the service to stop it
            }
        } else {
            Intent(applicationContext, RunningService::class.java).also {
                it.action = RunningService.Actions.START.toString()
                isServiceRunning = true
                startService(it) // Start the service to start it
            }
        }
    }


}
