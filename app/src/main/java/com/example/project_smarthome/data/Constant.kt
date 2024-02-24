package com.example.project_smarthome.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Light
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.project_smarthome.R
import com.example.project_smarthome.ui.IconPack
import com.example.project_smarthome.ui.iconpack.Valve

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