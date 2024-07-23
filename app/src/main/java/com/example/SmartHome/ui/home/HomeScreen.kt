package com.example.SmartHome.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Devices
import androidx.compose.material.icons.outlined.PowerSettingsNew
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.SmartHome.SmartHomeTopAppBar
import com.example.SmartHome.data.Device.Device
import com.example.SmartHome.data.Device.DeviceStatus
import com.example.SmartHome.data.Device.mockDeviceList
import com.example.SmartHome.data.mappingIcon
import com.example.SmartHome.data.Device.mockDeviceList
import com.example.SmartHome.R


enum class AddSmartDeviceScreen() {
    Main,
    DeviceSetting,
    DeviceWIFISetting,
    Complete
}

@Composable
fun SmartHomeMainScreen(
    modifier: Modifier = Modifier,
    onClickAddButton: () -> Unit
) {
    Scaffold(
        topBar = {
            SmartHomeTopAppBar(
                title = "SmartHome",
                canNavigateBack = false
            )
        }
    ) {innerPadding ->
        Column(modifier = modifier.padding(innerPadding)) {
            Button(
                modifier = Modifier.padding(12.dp),
                onClick = onClickAddButton
            ) {
                Text(
                    text = stringResource(R.string.add_devices),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(4.dp)
                )
            }
            DeviceGridScreen(
                devices = mockDeviceList
            )
        }
    }
}

@Composable
fun DeviceStatusCard(modifier: Modifier = Modifier, device: Device) {
    val cardColor: Color = if (device.status == DeviceStatus.ON) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.secondary
    }
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    mappingIcon.get(device.category) ?: Icons.Outlined.Devices,
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically),
                    contentDescription = null
                )
                Icon(
                    Icons.Outlined.PowerSettingsNew,
                    modifier = Modifier
                        .clickable { },
                    contentDescription = null
                )
            }
            Column {
                Text(
                    text = device.location,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = device.category,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = when (device.status) {
                        DeviceStatus.ON -> {
                            "ON"
                        }

                        DeviceStatus.OFF -> {
                            "OFF"
                        }

                        else -> {
                            "Loading.."
                        }
                    },
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun DeviceGridScreen(modifier: Modifier = Modifier, devices: List<Device>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(12.dp)
    ) {
        items(items = devices, key = { device -> device.id }) { device ->
            DeviceStatusCard(
                modifier = Modifier.fillMaxSize(),
                device = device
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmartHomeAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.home_title),
                fontSize = 20.sp
            )
        }
    )
}

//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen(Modifier.fillMaxSize())
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DeviceCardPreview() {
//    DeviceStatusCard(
//        modifier = Modifier,
//        Device(
//            id = 1,
//            category = "가스레인지",
//            location = "거실",
//            status = DeviceStatus.ON
//        )
//    )
//}

@Preview(showBackground = true)
@Composable
fun DeviceGridScreenPreview() {
    SmartHomeMainScreen(modifier = Modifier.fillMaxSize(), onClickAddButton = {})
}

