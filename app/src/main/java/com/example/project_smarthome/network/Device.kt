package com.example.project_smarthome.network

enum class DeviceStatus {
    ON, OFF, LOADING
}

data class Device(
    val id: String,
    val category: String,
    val location: String,
    val status: DeviceStatus
)