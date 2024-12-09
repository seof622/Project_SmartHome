package com.example.SmartHome.ui.home

import androidx.lifecycle.ViewModel
import com.example.SmartHome.data.Device.Device
import com.example.SmartHome.data.Device.DeviceRepository
import kotlinx.coroutines.flow.Flow

class HomeViewModel(private val deviceRepository: DeviceRepository) : ViewModel(){
    fun getAllDevices() : Flow<List<Device>> = deviceRepository.getAllDeviceStream()
    fun getDeviceInfo(id : Int) : Flow<Device?> = deviceRepository.getDeviceStream(id)
}