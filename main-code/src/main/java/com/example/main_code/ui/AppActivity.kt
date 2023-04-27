package com.example.main_code.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.main_code.R
import com.example.main_code.databinding.ActivityAppBinding
import com.example.main_code.util.ApplicationType
import com.example.main_code.viewmodel.AppViewModel

class AppActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAppBinding.inflate(layoutInflater)
    }

    private val charsViewModel by lazy {
        ViewModelProvider(this)[AppViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        charsViewModel.charactersType = intent.getSerializableExtra("APP_TYPE") as ApplicationType

        val host = supportFragmentManager.findFragmentById(R.id.frag_container) as NavHostFragment
        setupActionBarWithNavController(host.navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.frag_container).navigateUp() || super.onSupportNavigateUp()
    }
}