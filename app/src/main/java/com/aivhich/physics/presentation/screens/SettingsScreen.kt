package com.aivhich.physics.presentation.screens

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aivhich.physics.presentation.vm.SettingsViewModel
import com.aivhich.zaryaui.BodyText
import com.aivhich.zaryaui.HeadlineText
import com.aivhich.zaryaui.MainButton

@Composable
fun SettingsScreen(vm: SettingsViewModel = hiltViewModel()) {

    val activity = (LocalContext.current as? Activity)
    LaunchedEffect(key1 = "Unit1") {
        vm.initModel()
    }

    Scaffold(
        topBar = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .shadow(
                        10.dp,
                        RectangleShape,
                        clip = true,
                        ambientColor = MaterialTheme.colorScheme.tertiary,
                        spotColor = MaterialTheme.colorScheme.tertiary
                    )
                    .background(MaterialTheme.colorScheme.background)
                    .padding(10.dp, 0.dp, 10.dp, 0.dp)
                    .height(64.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HeadlineText(text = "Настройки")
            }
        }) { pd ->
        Surface(Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .padding(pd)
                    .padding(10.dp, 15.dp, 10.dp, 5.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MainButton(
                    backgroundColor = Color.Red, 
                    contentColor = MaterialTheme.colorScheme.background,
                    onClick = {
                        vm.deleteUserFromServer(activity)
                    }) {
                    BodyText(text = "Удалить аккаунт")
                }
            }
        }
    }
}