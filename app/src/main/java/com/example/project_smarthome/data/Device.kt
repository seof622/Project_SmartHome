package com.example.project_smarthome.data

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class DeviceStatus {
    ON, OFF, LOADING
}

@Entity(tableName = "devices")
data class Device (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val category: String,
    val location: String,
    val status: DeviceStatus
)