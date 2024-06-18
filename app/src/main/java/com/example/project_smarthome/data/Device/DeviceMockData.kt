package com.example.project_smarthome.data.Device

val mockDeviceList: List<Device> = listOf<Device>(
    Device(
        id = 1,
        category = "Light",
        location = "LivingRoom",
        status = DeviceStatus.ON
    ),
    Device(
        id = 2,
        category = "Gas Range",
        location = "Kitchen",
        status = DeviceStatus.ON
    ),
    Device(
        id = 3,
        category = "Light",
        location = "태현 방",
        status = DeviceStatus.OFF
    ),
    Device(
        id = 4,
        category = "Tv",
        location = "LivingRoom",
        status = DeviceStatus.ON
    )
)