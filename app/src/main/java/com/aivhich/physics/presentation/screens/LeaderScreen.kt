package com.aivhich.physics.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aivhich.physics.common.UIStatus
import com.aivhich.physics.presentation.custom_components.LoadingPage
import com.aivhich.physics.presentation.custom_components.UserCard
import com.aivhich.physics.presentation.vm.LeadersViewModel
import com.aivhich.zaryaui.HeadlineText
import com.aivhich.zaryaui.SubText

@Composable
fun LeaderScreen(vm: LeadersViewModel) {
    val name by vm.name.observeAsState()
    val place by vm.place.observeAsState()
    val points by vm.points.observeAsState()
    val users by vm.users.observeAsState()
    val userPoints by vm.userPoints.observeAsState()
    val uistate by vm.uistate.observeAsState()


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
                HeadlineText(text = "Лидеры")
            }
        }) { pd ->
        Surface(Modifier.fillMaxSize()) {

            if (uistate == UIStatus.PRESENT) {
                Column(
                    modifier = Modifier
                        .padding(pd)
                        .padding(10.dp, 15.dp, 10.dp, 5.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {

                    SubText(
                        text = "Ваш результат",
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(5.dp, 5.dp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    UserCard(
                        place = place ?: 101,
                        name = name.toString(),
                        points = points?.toInt() ?: 0
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    SubText(
                        text = "Топ-100 за эту неделю",
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(5.dp, 5.dp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    LazyColumn() {
                        itemsIndexed(users ?: listOf()) { i, el ->
                            UserCard(
                                place = i + 1,
                                name = "${el.name} ${el.surname}",
                                points = userPoints?.get(el.id)?.toInt() ?: 0
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                        }
                    }
                }
            } else {
                LoadingPage(pd)
            }
        }
    }
}