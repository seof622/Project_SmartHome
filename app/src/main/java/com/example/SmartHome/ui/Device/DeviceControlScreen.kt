package com.example.SmartHome.ui.Device

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.SmartHome.R
import com.example.SmartHome.SmartHomeTopAppBar
import com.example.SmartHome.data.Device.Device
import com.example.SmartHome.data.Device.DeviceStatus
import com.example.SmartHome.ui.AppViewModelProvider

@Composable
fun DeviceControlScreen(
    device: Device?,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            SmartHomeTopAppBar(
                title = stringResource(R.string.device_control_title),
                canNavigateBack = false
            )
        }
    ) { innerPadding ->
        DeviceControlBody(device = device, modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        )
    }
}

@Composable
fun DeviceControlBody(
    device: Device?,
    viewModel: DeviceControlViewModel = viewModel(
        factory = AppViewModelProvider.deviceControlFactory
    ),
    modifier: Modifier = Modifier
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = device?.category ?: "Category: None"
            )

            Text(
                text = device?.location ?: "Location: None"
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            RemoteButton(btnText = "ON", onClickEvent = {
                viewModel.onBtnClickEvent("device/control/${device?.id}")
            })
            Spacer(modifier = Modifier.size(16.dp))
            RemoteButton(btnText = "OFF", onClickEvent = {
                viewModel.offBtnClickEvent("device/control/${device?.id}")
            })
        }
    }
}

@Composable
fun RemoteButton(
    btnText: String,
    onClickEvent: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        shape = RoundedCornerShape(16.dp),
        onClick = onClickEvent
    ) {
        Text(
            btnText,
            fontSize = 16.sp,
            modifier = Modifier.padding(12.dp)
        )
    }
}

@Preview
@Composable
fun DeviceControlScreenPreview() {
    DeviceControlScreen(device = Device(0, "TV", "Living Room", DeviceStatus.ON))
}