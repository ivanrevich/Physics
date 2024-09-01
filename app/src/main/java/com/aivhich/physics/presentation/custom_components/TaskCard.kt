package com.aivhich.physics.presentation.custom_components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aivhich.physics.R
import com.aivhich.zaryaui.BodyText
import com.aivhich.zaryaui.PointsPanel

@Composable
fun TaskCard(html:String) {
    Column(
        Modifier
        .fillMaxWidth()
        .height(IntrinsicSize.Min)
        .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(15.dp))
    ){
        Row{
            BodyText(text = "1/10")
            PointsPanel(
                text = "250",
                painter = painterResource(id = R.drawable.baseline_bolt_24)
            )
        }
        HtmlText(html)
        //formcomponent should be here
    }
}