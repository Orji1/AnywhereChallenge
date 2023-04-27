package com.example.main_code

import android.content.Context
import android.content.Intent
import com.example.main_code.ui.AppActivity
import com.example.main_code.util.ApplicationType

interface IBaseApp {
    fun launchApp(context: Context, applicationType: ApplicationType)
}

object BaseApp : IBaseApp {
    override fun launchApp(context: Context, applicationType: ApplicationType) {
        Intent(context, AppActivity::class.java).apply {
            putExtra("APP_TYPE", applicationType)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(this)
        }
    }

}