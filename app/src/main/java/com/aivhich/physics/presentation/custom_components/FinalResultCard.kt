package com.aivhich.physics.presentation.custom_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aivhich.physics.R
import com.aivhich.zaryaui.BodyText
import com.aivhich.zaryaui.SubText
import retrofit2.http.Body

@Composable
fun FinalResultCard(result: String, success: Boolean) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Ваш результат",
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = result,
                fontSize = 64.sp,
                fontWeight = FontWeight.ExtraBold,
                color = if (success) MaterialTheme.colorScheme.primary else Color(0xFFE53935)
            )
            Icon(
                painter = painterResource(id = R.drawable.baseline_bolt_24),
                contentDescription = "",
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.tertiary
            )
        }
        if(!success) {
            Spacer(modifier = Modifier.height(40.dp))
            BodyText(
                text = "Вы должны перепройти этот уровень,\n так как не набрали 50% очков",
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
    }
}