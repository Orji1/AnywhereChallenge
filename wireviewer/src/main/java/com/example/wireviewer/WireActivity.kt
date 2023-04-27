package com.example.wireviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.main_code.BaseApp
import com.example.main_code.util.ApplicationType
import com.example.wireviewer.databinding.ActivityWireBinding

class WireActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityWireBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        BaseApp.launchApp(applicationContext, ApplicationType.THEWIRE())
    }
}