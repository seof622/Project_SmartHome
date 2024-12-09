package com.example.SmartHome.data

import android.content.Context
import com.example.SmartHome.data.Device.DeviceDatabase
import com.example.SmartHome.data.Device.DeviceRepository
import com.example.SmartHome.data.Device.SmartDeviceRepository
import com.example.SmartHome.data.Network.MQTTRepository

interface AppContainer {
    val deviceRepository: DeviceRepository
    val mqttRepository: MQTTRepository
}

class AppDataContainer(private val context: Context): AppContainer {
    override val deviceRepository: DeviceRepository by lazy {
        SmartDeviceRepository(DeviceDatabase.getDatabase(context).deviceDao())
    }
    override val mqttRepository: MQTTRepository by lazy {
        MQTTRepository()
    }
}