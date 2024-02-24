package com.example.project_smarthome.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.outlined.Devices
import androidx.compose.material.icons.outlined.PowerSettingsNew
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_smarthome.R
import com.example.project_smarthome.data.mappingIcon
import com.example.project_smarthome.data.mockDeviceList
import com.example.project_smarthome.data.translateKorean
import com.example.project_smarthome.network.Device
import com.example.project_smarthome.network.DeviceStatus

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold (topBar = { SmartHomeAppBar() }) {
        DeviceGridScreen(
            modifier = Modifier.padding(it),
            devices = mockDeviceList
        )
    }
}

@Composable
fun DeviceStatusCard(modifier: Modifier = Modifier, device: Device) {
    val cardColor: Color = if (device.status == DeviceStatus.ON) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.secondary
    }
    Card (
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ){
        Column (
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
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
                        .clickable {  },
                    contentDescription = null
                )
            }
            Column {
                Text(
                    text = translateKorean.get(device.location) ?: device.location,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = translateKorean.get(device.category) ?: device.category,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = when (device.status) {
                        DeviceStatus.ON -> {
                            "켜짐"
                        }
                        DeviceStatus.OFF -> {
                            "꺼짐"
                        }
                        else -> {
                            "로딩 중"
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
        items(items = devices, key = {device -> device.id}) {device->
            DeviceStatusCard(
                modifier = Modifier.fillMaxSize(),
                device = device
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmartHomeAppBar(modifier : Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.HomeScreenTitle),
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
//            id = "1",
//            category = "가스레인지",
//            location = "거실",
//            status = "on"
//            )
//    )
//}

@Preview(showBackground = true)
@Composable
fun DeviceGridScreenPreview() {
    DeviceGridScreen(modifier = Modifier.fillMaxSize(), devices = mockDeviceList)
}