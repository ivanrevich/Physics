package com.aivhich.physics.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aivhich.physics.R
import com.aivhich.physics.presentation.vm.ProfileViewModel
import com.aivhich.zaryaui.AvatarCircle
import com.aivhich.zaryaui.CarouselWithIconText
import com.aivhich.zaryaui.FieldBoxWithText
import com.aivhich.zaryaui.HeadlineText
import com.aivhich.zaryaui.IconButton
import com.aivhich.zaryaui.PointsPanel
import com.aivhich.zaryaui.StatisticBlockPanel
import com.aivhich.zaryaui.SubText
import com.aivhich.zaryaui.SubheadingText
import com.aivhich.zaryaui.UsualPanel
import com.aivhich.zaryaui.models.CarouselIconItem

@Composable
fun ProfileScreen(vm:ProfileViewModel = hiltViewModel()) {
    val name by vm.name.observeAsState()
    val email by vm.email.observeAsState()
    Scaffold(
        topBar = {
            Row(
                Modifier.fillMaxWidth().padding(10.dp, 5.dp, 10.dp, 0.dp).height(54.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    painter = painterResource(id = R.drawable.baseline_notifications_24),
                    size = 28.dp,
                    onClick = {},
                )
                IconButton(
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    painter = painterResource(id = R.drawable.baseline_settings_24),
                    size = 28.dp,
                    onClick = {},
                )
            }
        }) { pd ->
        Surface {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(10.dp, 0.dp, 10.dp, 5.dp)
                    .fillMaxSize()
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(pd),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AvatarCircle(
                        ABname = (name?.split(" ")
                            ?.map { el -> el[0].uppercase() })?.joinToString("")
                            ?: "",
                        background = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.size(108.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Column(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        SubheadingText(text = name ?: "")
                        PointsPanel(
                            text = "250",
                            painter = painterResource(id = R.drawable.baseline_bolt_24),
                        )
                    }
                }
                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    UsualPanel(
                        subtext = "Статистика",
                        background = MaterialTheme.colorScheme.background,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(136.dp)
                    ) {
                        Row(Modifier.fillMaxWidth()) {
                            StatisticBlockPanel(
                                "Решено", "35", "задач",
                                modifier = Modifier.weight(1f)
                            )
                            VerticalDivider(Modifier.width(1.dp))
                            StatisticBlockPanel(
                                "Решает", "76%", "задач",
                                modifier = Modifier.weight(1f)
                            )
                            VerticalDivider(Modifier.width(1.dp))
                            StatisticBlockPanel(
                                "Выучено", "17", "формул",
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    UsualPanel(
                        background = MaterialTheme.colorScheme.background,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        FieldBoxWithText("Почта", email ?: "")
                        HorizontalDivider()
//                    FieldBoxWithText("Класс", "10")
//                    HorizontalDivider()
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(15.dp, 15.dp, 15.dp, 15.dp)
                                .height(128.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Top
                        ) {
                            SubText(
                                text = "Достижения",
                                fontWeight = FontWeight.Medium,
                                color = Color.Gray,
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            CarouselWithIconText(
                                listOf(
                                    CarouselIconItem(
                                        "Знаток формул",
                                        painterResource(id = R.drawable.baseline_bolt_24)
                                    ),
                                    CarouselIconItem(
                                        "Знаток формул",
                                        painterResource(id = R.drawable.baseline_bolt_24)
                                    ),
                                    CarouselIconItem(
                                        "Знаток формул",
                                        painterResource(id = R.drawable.baseline_bolt_24)
                                    ),
                                    CarouselIconItem(
                                        "Знаток формул",
                                        painterResource(id = R.drawable.baseline_bolt_24)
                                    )
                                )
                            )
                        }
//                    HorizontalDivider()
//                    Column(
//                        Modifier
//                            .fillMaxWidth()
//                            .padding(15.dp, 15.dp, 15.dp, 15.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center
//                    ){
//                        MainButton(
//                            backgroundColor = MaterialTheme.colorScheme.onBackground,
//                            contentColor = MaterialTheme.colorScheme.tertiary,
//                            onClick = { /*TODO*/ },
//                            shape = RoundedCornerShape(50),
//                            modifier = Modifier.height(48.dp).fillMaxWidth()) {
//                            BodyText(text = "Выйти из аккаунта")
//                        }
//                    }
                    }
                }
            }
        }
    }
}
