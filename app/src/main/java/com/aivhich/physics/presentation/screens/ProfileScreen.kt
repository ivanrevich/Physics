package com.aivhich.physics.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aivhich.physics.R
import com.aivhich.zaryaui.AvatarCircle
import com.aivhich.zaryaui.HeadlineText
import com.aivhich.zaryaui.IconButton
import com.aivhich.zaryaui.StatisticBlockPanel
import com.aivhich.zaryaui.UsualPanel

@Composable
fun ProfileScreen() {
    Surface {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    painter = painterResource(id = R.drawable.baseline_notifications_24),
                    onClick = {},
                )
                IconButton(
                    painter = painterResource(id = R.drawable.baseline_settings_24),
                    onClick = {},
                )
            }
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                AvatarCircle(
                    ABname = "",
                    background = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.size(120.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Column(Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center){
                    HeadlineText(text = "Иван Иванов")
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_person_24),
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.height(3.dp))
                UsualPanel(
                    subtext = "Статистика",
                    background = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(154.dp)
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
                Spacer(modifier = Modifier.height(30.dp))
                UsualPanel(
                    background = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {
                }
            }
        }
    }
}
