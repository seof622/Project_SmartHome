package com.example.SmartHome.ui.navigation

import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.SmartHome.data.Device.Device
import com.example.SmartHome.data.Device.mockDeviceList
import com.example.SmartHome.ui.Device.DeviceControlScreen
import com.example.SmartHome.ui.Device.DeviceSettingScreen
import com.example.SmartHome.ui.Device.DeviceWifiSettingScreen
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
                onClickAddButton = {
                    navController.navigate(AddSmartDeviceScreen.DeviceWIFISetting.name)
                },
                onClickCardEvent = { device->
                    navController.navigate("${AddSmartDeviceScreen.DeviceControl.name}/${device.id}")
                }
            )
        }

        composable(route = AddSmartDeviceScreen.DeviceWIFISetting.name) {
            DeviceWifiSettingScreen(
                onClickNextBtn = {
                    navController.navigate(AddSmartDeviceScreen.DeviceSetting.name)
                }
            )
        }

        composable(route = AddSmartDeviceScreen.DeviceSetting.name) {
            DeviceSettingScreen(
                navigateBack = { navController.popBackStack() },
                onClickNext = {
                    navController.navigate(AddSmartDeviceScreen.Main.name)
                }
            )
        }

        composable(
            route = "${AddSmartDeviceScreen.DeviceControl.name}/{deviceId}",
            arguments = listOf(navArgument("deviceId") { type = NavType.StringType })
        ) { backStackEntry->
            val deviceId = backStackEntry.arguments?.getString("deviceId")
            val device = mockDeviceList[(deviceId?.toInt() ?: 1) - 1]
            DeviceControlScreen(
                device = device,
            )
        }
    }
}