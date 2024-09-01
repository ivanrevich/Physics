package com.aivhich.physics.presentation.custom_components

import android.widget.TextView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.core.text.HtmlCompat
import com.aivhich.physics.R
import com.aivhich.zaryaui.BodyText
import com.aivhich.zaryaui.HeadlineText
import com.aivhich.zaryaui.PointsPanel
import com.aivhich.zaryaui.SubText
import com.aivhich.zaryaui.SubheadingText
import com.aivhich.zaryaui.UsualPanel


@Composable
fun LevelItem(text: String) {
    Box(Modifier.fillMaxWidth()) {
        SubText(text = text, modifier = Modifier.align(Alignment.Center))
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
                .zIndex(0f)
        ) {
            val strokeWidth = 8.dp
            val color = Color.Green
            val strokeWidthPx = density.run { strokeWidth.toPx() }
            val height = size.height
            drawLine(
                start = Offset(x = 32.dp.toPx(), y = 0f),
                end = Offset(x = 32.dp.toPx(), y = height),
                color = color,
                strokeWidth = strokeWidthPx,
            )
        }
    }
}

@Composable
fun LessonItem(title:String, time:String, points:String,
               onClick:()->Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        UsualPanel(
            background = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1f)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp, 10.dp)
                    .height(IntrinsicSize.Max)
            ) {
                Row(horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxHeight()) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_bolt_24),
                        contentDescription = "",
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .size(54.dp)
                            .border(5.dp, MaterialTheme.colorScheme.tertiary, CircleShape)
                            .padding(5.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        SubheadingText(text = title,
                            fontWeight = FontWeight.W600,
                            textAlign = TextAlign.Start)
                        SubText(text = "$time мин.")
                    }
                }
                PointsPanel(
                    text = points,
                    painter = painterResource(id = R.drawable.baseline_bolt_24),
                    true
                )
            }
        }

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .zIndex(0f)
        ) {
            val strokeWidth = 8.dp
            val color = Color.Green
            val strokeWidthPx = density.run { strokeWidth.toPx() }
            val height = size.height
            drawLine(
                start = Offset(x = 32.dp.toPx(), y = 0f),
                end = Offset(x = 32.dp.toPx(), y = height),
                color = color,
                strokeWidth = strokeWidthPx,
            )
        }
    }
}

@Composable
fun HtmlText(html: String, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context -> TextView(context) },
        update = { it.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT) }
    )
}