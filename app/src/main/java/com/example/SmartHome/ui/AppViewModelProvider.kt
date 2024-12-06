package com.example.SmartHome.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.SmartHome.SmartHomeApplication
import com.example.SmartHome.ui.Device.DeviceSettingViewModel
import com.example.SmartHome.ui.Device.DeviceWifiSettingViewModel
import com.example.SmartHome.ui.home.MQTTViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            DeviceSettingViewModel(smartHomeApplication().container.deviceRepository)
        }
        initializer {
            DeviceWifiSettingViewModel()
        }
        initializer {
            MQTTViewModel()
        }
    }

}

fun CreationExtras.smartHomeApplication(): SmartHomeApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SmartHomeApplication)