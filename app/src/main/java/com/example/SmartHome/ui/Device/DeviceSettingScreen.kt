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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.SmartHome.R
import com.example.SmartHome.SmartHomeTopAppBar
import com.example.SmartHome.data.Device.DeviceCharacter
import com.example.SmartHome.data.deviceCategoryArray
import com.example.SmartHome.data.deviceLocationArray
import com.example.SmartHome.data.menus
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
                title = stringResource(R.string.device_setting_title),
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
                    id = "category",
                    dropMenuState = viewModel.dropDownMenuStates.value["category"]!!,
                    onDropMenuToggle = viewModel::onDropdownMenuToggle,
                    onSelectMenu = viewModel::updateTextField,
                    onDismiss = viewModel::onDropdownMenuDismiss
                )
                DeviceSettingDropDownMenu(
                    id = "location",
                    dropMenuState = viewModel.dropDownMenuStates.value["location"]!!,
                    onDropMenuToggle = viewModel::onDropdownMenuToggle,
                    onSelectMenu = viewModel::updateTextField,
                    onDismiss = viewModel::onDropdownMenuDismiss
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
    id: String,
    dropMenuState: DropMenuState,
    onDropMenuToggle: (String) -> Unit,
    onSelectMenu: (String, String) -> Unit,
    onDismiss: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = dropMenuState.isExpanded,
            onExpandedChange = { onDropMenuToggle(id) }
        ) {
            TextField(
                value = dropMenuState.selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropMenuState.isExpanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                label = { Text(text = id) }
            )

            ExposedDropdownMenu(
                expanded = dropMenuState.isExpanded,
                onDismissRequest = { onDismiss(id) }
            ) {
                menus[id]?.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            onSelectMenu(id, item)
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