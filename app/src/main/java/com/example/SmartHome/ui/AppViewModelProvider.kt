package com.example.SmartHome.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.SmartHome.SmartHomeApplication
import com.example.SmartHome.ui.Device.DeviceControlViewModel
import com.example.SmartHome.ui.Device.DeviceInfoSettingViewModel
import com.example.SmartHome.ui.Device.DeviceWifiSettingViewModel

object AppViewModelProvider {
    val deviceSettingFactory = viewModelFactory {
        initializer {
            DeviceInfoSettingViewModel(smartHomeApplication().container.deviceRepository)
        }
    }

    val deviceWifiSettingFactory = viewModelFactory {
        initializer {
            DeviceWifiSettingViewModel()
        }
    }

    val deviceControlFactory = viewModelFactory {
        initializer {
            DeviceControlViewModel(smartHomeApplication().container.mqttRepository)
        }
    }
}

fun CreationExtras.smartHomeApplication(): SmartHomeApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SmartHomeApplication)