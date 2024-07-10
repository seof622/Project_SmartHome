package com.example.SmartHome.data

import android.content.Context
import com.example.SmartHome.data.Device.DeviceDatabase
import com.example.SmartHome.data.Device.DeviceRepository
import com.example.SmartHome.data.Device.SmartDeviceRepository

interface AppContainer {
    val deviceRepository: DeviceRepository
}

class AppDataContainer(private val context: Context): AppContainer {
    override val deviceRepository: DeviceRepository by lazy {
        SmartDeviceRepository(DeviceDatabase.getDatabase(context).deviceDao())
    }
}