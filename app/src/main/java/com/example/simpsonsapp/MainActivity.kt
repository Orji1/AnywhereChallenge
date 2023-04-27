package com.example.simpsonsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.main_code.BaseApp
import com.example.main_code.util.ApplicationType
import com.example.simpsonsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        BaseApp.launchApp(applicationContext, ApplicationType.SIMPSONS())
    }
}