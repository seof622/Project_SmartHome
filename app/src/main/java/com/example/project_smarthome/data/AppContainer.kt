package com.example.project_smarthome.data

import android.content.Context
import com.example.project_smarthome.data.Device.DeviceDatabase
import com.example.project_smarthome.data.Device.DeviceRepository
import com.example.project_smarthome.data.Device.SmartDeviceRepository

interface AppContainer {
    val deviceRepository: DeviceRepository
}

class AppDataContainer(private val context: Context): AppContainer {
    override val deviceRepository: DeviceRepository by lazy {
        SmartDeviceRepository(DeviceDatabase.getDatabase(context).deviceDao())
    }
}