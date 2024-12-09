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
    var deviceCharacter: Map<String, String> = mapOf(
        "category" to "Device Category",
        "location" to "Device Location"
    ),
    val status: DeviceStatus = DeviceStatus.OFF
)

class DeviceInfoSettingViewModel(private val deviceRepository: DeviceRepository) : ViewModel() {
    private var _deviceDetails by mutableStateOf(DeviceDetails())
    private var _deviceUiState by mutableStateOf(DeviceUiState().copy(deviceDetails = _deviceDetails))
    private val _dropDownMenuStates = mutableStateOf(
        mapOf(
            "category" to DropMenuState(),
            "location" to DropMenuState()
        )
    )

    val deviceUiState get() = _deviceUiState
    val dropDownMenuStates get() = _dropDownMenuStates

    private fun validateInput(uiState: DeviceDetails): Boolean {
        return with(uiState) {
            deviceCategoryArray.contains(deviceCharacter["category"]) and
                    deviceLocationArray.contains(deviceCharacter["location"])
        }
    }

    fun updateTextField(id:String, text:String) {
        _dropDownMenuStates.value = _dropDownMenuStates.value.mapValues {
            if (it.key == id) it.value.copy(selectedText = text, isExpanded = false)
            else it.value
        }

        _deviceDetails = _deviceDetails.copy(
            deviceCharacter = _deviceDetails.deviceCharacter.mapValues {
                if(it.key == id) text
                else it.value
            }
        )

        _deviceUiState = _deviceUiState.copy(
            deviceDetails = _deviceDetails,
            isEntryValid = validateInput(_deviceDetails)
        )
    }

    fun onDropdownMenuToggle(id:String) {
        _dropDownMenuStates.value = _dropDownMenuStates.value.mapValues {
            if (it.key == id) it.value.copy(isExpanded = !it.value.isExpanded)
            else it.value
        }
    }

    fun onDropdownMenuDismiss(id:String) {
        _dropDownMenuStates.value = _dropDownMenuStates.value.mapValues {
            if (it.key == id) it.value.copy(isExpanded = false)
            else it.value
        }
    }

    suspend fun saveDevice() {
        if (deviceUiState.isEntryValid) {
            deviceRepository.insertDevice(deviceUiState.deviceDetails.toDevice())
        }
    }

}


fun CreationExtras.smartHomeApplication(): SmartHomeApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SmartHomeApplication)

fun DeviceDetails.toDevice(): Device = Device(
    id = id,
    category = deviceCharacter["category"]!!,
    location = deviceCharacter["location"]!!,
    status = status
)