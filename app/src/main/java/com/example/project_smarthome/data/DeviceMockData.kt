package com.example.project_smarthome.data

import com.example.project_smarthome.network.Device
import com.example.project_smarthome.network.DeviceStatus

val mockDeviceList: List<Device> = listOf<Device>(
    Device(
        id = "1",
        category = "전등",
        location = "거실",
        status = DeviceStatus.ON
    ),
    Device(
        id = "2",
        category = "가스 레인지",
        location = "주방",
        status = DeviceStatus.ON
    ),
    Device(
        id = "3",
        category = "전등",
        location = "태현 방",
        status = DeviceStatus.OFF
    ),
    Device(
        id = "4",
        category = "TV",
        location = "거실",
        status = DeviceStatus.ON
    )
)