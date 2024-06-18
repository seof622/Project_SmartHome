package com.example.project_smarthome.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.project_smarthome.SmartHomeApplication
import com.example.project_smarthome.ui.Device.DeviceSettingViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            DeviceSettingViewModel(smartHomeApplication().container.deviceRepository)
        }
    }
}

fun CreationExtras.smartHomeApplication(): SmartHomeApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SmartHomeApplication)