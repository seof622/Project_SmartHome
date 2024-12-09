package com.example.SmartHome.ui.Device

import androidx.lifecycle.ViewModel
import com.example.SmartHome.data.Network.MQTTRepository


class DeviceControlViewModel(private val mqttRepository: MQTTRepository): ViewModel() {
    init {
        mqttRepository.connect()
    }

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