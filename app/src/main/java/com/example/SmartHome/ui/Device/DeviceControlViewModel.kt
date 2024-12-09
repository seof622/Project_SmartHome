package com.example.SmartHome.ui.Device

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.example.SmartHome.data.Device.Device
import com.example.SmartHome.data.Device.DeviceRepository
import com.example.SmartHome.data.Network.MQTTRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class DeviceControlViewModel(
    private val mqttRepository: MQTTRepository,
    private val deviceRepository: DeviceRepository,
): ViewModel() {
    init {
        mqttRepository.connect()
    }

    fun getDeviceInfo(id : Int) : Flow<Device?> = deviceRepository.getDeviceStream(id)

    override fun onCleared() {
        super.onCleared()
        mqttRepository.disconnect()
    }

    fun onBtnClickEvent(topic : String) {
        mqttRepository.publish(topic, "On".toByteArray())
    }

    fun offBtnClickEvent(topic : String) {
        mqttRepository.publish(topic, "Off".toByteArray())
    }
}