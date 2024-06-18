package com.example.project_smarthome

import android.app.Application
import com.example.project_smarthome.data.AppContainer
import com.example.project_smarthome.data.AppDataContainer

class SmartHomeApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}