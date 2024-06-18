package com.example.project_smarthome.data.Device

import kotlinx.coroutines.flow.Flow

interface DeviceRepository {
    fun getAllDeviceStream(): Flow<List<Device>>
    fun getDeviceStream(id: Int): Flow<Device?>
    suspend fun insertDevice(device: Device)
    suspend fun deleteDevice(device: Device)
    suspend fun updateDevice(device: Device)
}