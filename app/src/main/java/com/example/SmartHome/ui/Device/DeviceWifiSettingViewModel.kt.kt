package com.example.SmartHome.ui.Device

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class DeviceWifiInputState(
    val ssid: String = "",
    val password: String = "",
    val isPasswordShow: Boolean = false
)

class DeviceWifiSettingViewModel(): ViewModel() {
    private var _deviceWifiInputState by  mutableStateOf(DeviceWifiInputState())

    val deviceWifiInputState get() = _deviceWifiInputState

    fun setSSID(text: String) {
        _deviceWifiInputState = _deviceWifiInputState.copy(ssid = text)
    }

    fun setPassword(text: String) {
        _deviceWifiInputState = _deviceWifiInputState.copy(password = text)
    }

    fun toggleVisibleButton() {
        _deviceWifiInputState = _deviceWifiInputState.copy(
            isPasswordShow = !_deviceWifiInputState.isPasswordShow
        )
    }
    
    fun connectToDevice() {
        // TODO: WIFI를 기반으로 디바이스에 원격으로 WIFI 연결해주는 함수
    }
}