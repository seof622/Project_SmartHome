package com.example.SmartHome.ui.Device

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.SmartHome.SmartHomeApplication
import com.example.SmartHome.data.Device.Device
import com.example.SmartHome.data.Device.DeviceRepository
import com.example.SmartHome.data.Device.DeviceStatus
import com.example.SmartHome.data.deviceCategoryArray
import com.example.SmartHome.data.deviceLocationArray

data class DeviceUiState(
    val deviceDetails: DeviceDetails = DeviceDetails(),
    val isEntryValid: Boolean = false
)

data class DropMenuState(
    val isExpanded: Boolean = false,
    val selectedText: String = ""
)

data class DeviceDetails(
    val id: Int = 0,
    val category: String = "Device Category",
    val location: String = "Device Location",
    val status: DeviceStatus = DeviceStatus.OFF
)

class DeviceSettingViewModel(private val deviceRepository: DeviceRepository) : ViewModel() {
    var deviceUiState by mutableStateOf(DeviceUiState())
        private set

    var categoryMenuState by mutableStateOf(DropMenuState())
        private set
    var locationMenuState by mutableStateOf(DropMenuState())
        private set
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                DeviceSettingViewModel(smartHomeApplication().container.deviceRepository)
            }
        }
    }

    private fun validateInput(uiState: DeviceDetails = deviceUiState.deviceDetails): Boolean {
        return with(uiState) {
            deviceCategoryArray.contains(category) && deviceLocationArray.contains(location)
        }
    }

    fun updateDeviceUiState(deviceDetails: DeviceDetails) {
        deviceUiState = DeviceUiState(
            deviceDetails = deviceDetails,
            isEntryValid = validateInput(deviceDetails)
        )
    }

    fun clickCategoryMenu() {
        categoryMenuState = categoryMenuState.copy(
            isExpanded = !categoryMenuState.isExpanded
        )
    }

    fun clickLocationMenu() {
        locationMenuState = locationMenuState.copy(
            isExpanded = !locationMenuState.isExpanded
        )
    }

    fun updateCategoryText(text: String) {
        categoryMenuState = categoryMenuState.copy(
            selectedText = text
        )
    }

    fun updateLocationText(text: String) {
        locationMenuState = locationMenuState.copy(
            selectedText = text
        )
    }
    fun dropMenuDismiss(dropMenuState: DropMenuState) {
        if (dropMenuState == categoryMenuState) {
            categoryMenuState = categoryMenuState.copy(
                isExpanded = false
            )
        } else if (dropMenuState == locationMenuState) {
            locationMenuState = locationMenuState.copy(
                isExpanded = false
            )
        }
    }

    suspend fun saveDevice() {
        if (validateInput()) {
            deviceRepository.insertDevice(deviceUiState.deviceDetails.toDevice())
        }
    }

}

fun CreationExtras.smartHomeApplication(): SmartHomeApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SmartHomeApplication)

fun DeviceDetails.toDevice(): Device = Device(
    id = id,
    category = category,
    location = location,
    status = status
)