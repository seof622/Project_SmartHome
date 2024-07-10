package com.example.SmartHome.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.SmartHome.SmartHomeApplication
import com.example.SmartHome.ui.Device.DeviceSettingViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            DeviceSettingViewModel(smartHomeApplication().container.deviceRepository)
        }
    }
}

fun CreationExtras.smartHomeApplication(): SmartHomeApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SmartHomeApplication)