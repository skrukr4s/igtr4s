package com.r4s.igt.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.r4s.igt.R
import com.r4s.igt.utils.startMainActivity
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch


// Created by sebastian with ♥
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        launch {
            delay(3000)
            startMainActivity()
        }
    }

}
