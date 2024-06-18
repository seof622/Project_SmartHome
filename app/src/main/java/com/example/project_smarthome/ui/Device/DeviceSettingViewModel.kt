package com.example.project_smarthome.ui.Device

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.project_smarthome.data.Device.Device
import com.example.project_smarthome.data.Device.DeviceRepository
import com.example.project_smarthome.data.Device.DeviceStatus

data class DeviceUiState(
    val deviceDetails: DeviceDetails = DeviceDetails(),
    val isEntryValid: Boolean = false
)

data class DeviceDetails(
    val id: Int = 0,
    val category: String = "",
    val location: String = "",
    val status: DeviceStatus = DeviceStatus.OFF
)

class DeviceSettingViewModel(private val deviceRepository: DeviceRepository): ViewModel() {
    var deviceUiState by mutableStateOf(DeviceUiState())
        private set

    private fun validateInput(uiState: DeviceDetails = deviceUiState.deviceDetails): Boolean {
        return with(uiState) {
            category.isNotBlank() && location.isNotBlank()
        }
    }

    suspend fun saveDevice() {
        if (validateInput()) {
            deviceRepository.insertDevice(deviceUiState.deviceDetails.toDevice())
        }
    }
}

fun DeviceDetails.toDevice(): Device = Device(
    id = id,
    category = category,
    location = location,
    status = status
)