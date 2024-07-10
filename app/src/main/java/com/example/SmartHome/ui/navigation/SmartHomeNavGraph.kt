package com.example.SmartHome.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.SmartHome.ui.Device.DeviceSettingScreen
import com.example.SmartHome.ui.home.AddSmartDeviceScreen
import com.example.SmartHome.ui.home.SmartHomeMainScreen

@Composable
fun SmartHomeNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AddSmartDeviceScreen.Main.name
    ) {
        composable(route = AddSmartDeviceScreen.Main.name) {
            SmartHomeMainScreen(
                onClickAddButton = { navController.navigate(AddSmartDeviceScreen.DeviceSetting.name) }
            )
        }
        composable(route = AddSmartDeviceScreen.DeviceSetting.name) {
            DeviceSettingScreen(
                navigateBack = { navController.popBackStack() },
                onClickNext = {
                    navController.navigate(AddSmartDeviceScreen.Complete.name)
                }
            )
        }
    }
}