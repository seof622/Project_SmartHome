package com.example.SmartHome.ui.Device

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.SmartHome.ui.AppViewModelProvider

@Composable
fun DeviceWifiSettingScreen(
    modifier: Modifier = Modifier,
    onClickNextBtn: () -> Unit,
    viewModel: DeviceWifiSettingViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Scaffold (
        topBar = {}
    ) { innerPadding->
       DeviceWifiSettingBody(
           deviceWifiInputState = viewModel.deviceWifiInputState,
           ssidTextChange = viewModel::setSSID,
           passwordTextChange = viewModel::setPassword,
           clickVisibilityBtn = viewModel::toggleVisibleButton,
           onClickNextBtn = {
               viewModel.connectToDevice()
               onClickNextBtn()
           },
           modifier
               .fillMaxSize()
               .padding(innerPadding)
       )
    }
}

// TODO: textfield focus 기능: LOGCAT에서 FrameTracker error 뜨는거 고치기
//  다음으로 넘어가는 것 구현
@Composable
fun DeviceWifiSettingBody(
    deviceWifiInputState: DeviceWifiInputState,
    ssidTextChange: (String) -> Unit,
    passwordTextChange: (String) -> Unit,
    clickVisibilityBtn: () -> Unit,
    onClickNextBtn : () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
//    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .addFocusCleaner(focusManager)
    ){
        TextField(
            value = deviceWifiInputState.ssid,
            onValueChange = { ssidTextChange(it) },
            label = { Text(text = "SSID") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                }
                .padding(20.dp)
//                .focusRequester(focusRequester = focusRequester)
        )
        TextField(
            value = deviceWifiInputState.password,
            onValueChange = { passwordTextChange(it) },
            label = { Text(text =  "PASSWORD") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (deviceWifiInputState.isPasswordShow) VisualTransformation.None
                                    else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = clickVisibilityBtn) {
                    Icon(
                        imageVector = Icons.Outlined.Visibility,
                        contentDescription = null
                    )
                }
            },
            modifier = Modifier.padding(20.dp)
        )
        Button(
            onClick= onClickNextBtn,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .size(
                    with(LocalDensity.current) {textFieldSize.width.toDp()},
                    with(LocalDensity.current) {textFieldSize.height.toDp()}
                )
                .padding(20.dp),
        ) {
            Text("Next", fontSize = 16.sp)
        }
    }
}

fun Modifier.addFocusCleaner(focusManager: FocusManager): Modifier {
    return this.pointerInput(Unit) {
        detectTapGestures(onTap = {
            focusManager.clearFocus()
        })
    }
}

//@Preview
//@Composable
//fun DeviceWifiSettingBodyPreview() {
//    DeviceWifiSettingBody(
//        deviceWifiInputState = DeviceWifiInputState(),
//
//        Modifier.fillMaxSize()
//    )
//}