package com.example.project_smarthome.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.project_smarthome.ui.Device.DeviceSettingScreen
import com.example.project_smarthome.ui.home.SmartHomeMainScreen
import com.example.project_smarthome.ui.home.SmartHomeScreen

@Composable
fun SmartHomeNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = SmartHomeScreen.Main.name
    ) {
        composable(route = SmartHomeScreen.Main.name) {
            SmartHomeMainScreen(
                onClickAddButton = { navController.navigate(SmartHomeScreen.DeviceSetting.name) }
            )
        }
        composable(route = SmartHomeScreen.DeviceSetting.name) {
            DeviceSettingScreen(
                onClickNext = {navController.navigate(SmartHomeScreen.WIFISetting.name)}
            )
        }
    }
}