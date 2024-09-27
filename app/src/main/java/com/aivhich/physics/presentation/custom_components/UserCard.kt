package com.aivhich.physics.presentation.custom_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.aivhich.physics.R
import com.aivhich.zaryaui.BodyText
import com.aivhich.zaryaui.HeadlineText
import com.aivhich.zaryaui.PointsPanel
import com.aivhich.zaryaui.SubText
import com.aivhich.zaryaui.SubheadingText
import com.aivhich.zaryaui.UsualPanel

@Composable
fun UserCard(place: Int, name: String, points: Int) {

    UsualPanel(
        background = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .shadow(0.75.dp, RoundedCornerShape(20.dp))
            .zIndex(1f)
            .clip(RoundedCornerShape(20.dp))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp, 0.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SubheadingText(text = place.toString(), fontWeight = FontWeight.W900)
                Spacer(modifier = Modifier.width(10.dp))
                BodyText(text = name)
            }

            PointsPanel(
                text = points.toString(),
                painter = painterResource(id = R.drawable.baseline_bolt_24),
                true
            )
        }
    }
}