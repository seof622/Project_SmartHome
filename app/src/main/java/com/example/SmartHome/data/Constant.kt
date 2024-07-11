package com.example.SmartHome.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Light
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.SmartHome.ui.IconPack
import com.example.SmartHome.ui.iconpack.Valve

val mappingIcon = mapOf<String, ImageVector>(
    "Tv" to Icons.Outlined.Tv,
    "Light" to Icons.Outlined.Light,
    "Gas Range" to IconPack.Valve
)

val translateKorean = mapOf<String, String>(
    "Tv" to "티비",
    "Light" to "전등",
    "Gas Range" to "가스레인지",
    "LivingRoom" to "거실",
    "Kitchen" to "주방",
    "On" to "켜짐",
    "Off" to "꺼짐",
    "Loading" to "로딩 중"
)

val deviceCategoryArray: Array<String> = arrayOf("Tv", "Light", "Gas Range", "Woody Meals")
val deviceLocationArray: Array<String> = arrayOf("LivingRoom", "Kitchen", "Parent Room", "Sister Room")
val menus = mapOf<String, Array<String>>(
    "category" to deviceCategoryArray,
    "location" to deviceLocationArray
)
val mqttIP = "localHost"
val mqttPort = "1883"