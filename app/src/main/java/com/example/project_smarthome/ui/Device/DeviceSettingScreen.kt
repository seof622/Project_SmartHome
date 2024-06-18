package com.example.project_smarthome.ui.Device

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.project_smarthome.ui.theme.Project_SmartHomeTheme

@Composable
fun DeviceSettingScreen(
    modifier: Modifier = Modifier,
    onClickNext: () -> Unit
) {
    Project_SmartHomeTheme {
        Column (
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ){
            Column {
                Text(
                    text = "장치 타입"
                )
                Text(
                    text = "장치 위치"
                )
            }
            Button(
                onClick = onClickNext
            ) {
                Text(
                    "다음"
                )
            }
        }
    }
}