package com.example.SmartHome.ui.Device

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.SmartHome.SmartHomeTopAppBar
import com.example.SmartHome.data.Device.DeviceCharacter
import com.example.SmartHome.data.deviceCategoryArray
import com.example.SmartHome.data.deviceLocationArray
import com.example.SmartHome.ui.AppViewModelProvider
import kotlinx.coroutines.*

@Composable
fun DeviceSettingScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    onClickNext: () -> Unit,
    viewModel: DeviceSettingViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            SmartHomeTopAppBar(
                title = "Final: Device Character Set",
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                DeviceSettingDropDownMenu(
                    surfaceText = viewModel.deviceUiState.deviceDetails.category,
                    menus = deviceCategoryArray,
                    dropMenuState = viewModel.categoryMenuState,
                    deviceUiState = viewModel.deviceUiState,
                    deviceCharacter = DeviceCharacter.CATEGORY,
                    setDeviceDetails = viewModel::updateDeviceUiState,
                    onClickDropMenu = viewModel::clickCategoryMenu,
                    updateDropMenuText = viewModel::updateCategoryText,
                    onDismissCallback = viewModel::dropMenuDismiss
                )
                DeviceSettingDropDownMenu(
                    surfaceText = viewModel.deviceUiState.deviceDetails.location,
                    menus = deviceLocationArray,
                    dropMenuState = viewModel.locationMenuState,
                    deviceUiState = viewModel.deviceUiState,
                    deviceCharacter = DeviceCharacter.LOCATION,
                    setDeviceDetails = viewModel::updateDeviceUiState,
                    onClickDropMenu = viewModel::clickLocationMenu,
                    updateDropMenuText = viewModel::updateLocationText,
                    onDismissCallback = viewModel::dropMenuDismiss
                )
            }
            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveDevice()
                    }
                    onClickNext()
                }
            ) {
                Text(
                    "Next",
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceSettingDropDownMenu(
    surfaceText: String,
    menus: Array<String>,
    dropMenuState: DropMenuState,
    deviceUiState: DeviceUiState,
    setDeviceDetails: (DeviceDetails) -> Unit,
    onClickDropMenu: () -> Unit,
    updateDropMenuText: (String) -> Unit,
    onDismissCallback: (DropMenuState) -> Unit,
    deviceCharacter: DeviceCharacter,
    modifier: Modifier = Modifier
) {
    /*
    TODO - ViewModel하고 Drop Menu 연결 해야 함
     */
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = dropMenuState.isExpanded,
            onExpandedChange = { onClickDropMenu() }
        ) {
            TextField(
                value = dropMenuState.selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropMenuState.isExpanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                label = { Text(text = deviceCharacter.name) }
            )

            ExposedDropdownMenu(
                expanded = dropMenuState.isExpanded,
                onDismissRequest = { onDismissCallback(dropMenuState) }
            ) {
                menus.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            when (deviceCharacter) {
                                DeviceCharacter.CATEGORY ->
                                    setDeviceDetails(deviceUiState.deviceDetails.copy(category = item))

                                DeviceCharacter.LOCATION ->
                                    setDeviceDetails(deviceUiState.deviceDetails.copy(location = item))
                            }
                            updateDropMenuText(item)
                            onClickDropMenu()
                        }
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun DeviceSettingScreenPreview() {
    DeviceSettingScreen(
        modifier = Modifier.fillMaxSize(),
        navigateBack = { /*TODO*/ },
        onClickNext = {},
    )
}