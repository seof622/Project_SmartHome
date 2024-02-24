package com.example.project_smarthome.ui

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.project_smarthome.ui.iconpack.Valve
import kotlin.collections.List as ____KtList

public object IconPack

private var `__HouseConfiguration`: ____KtList<ImageVector>? = null

public val IconPack.`HouseConfiguration`: ____KtList<ImageVector>
  get() {
    if (`__HouseConfiguration` != null) {
      return `__HouseConfiguration`!!
    }
    `__HouseConfiguration`= listOf(Valve)
    return `__HouseConfiguration`!!
  }
