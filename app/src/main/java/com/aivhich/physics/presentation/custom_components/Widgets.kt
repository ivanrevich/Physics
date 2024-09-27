package com.aivhich.physics.presentation.custom_components

import android.webkit.WebView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import com.aivhich.physics.R
import com.aivhich.physics.presentation.vm.LessonViewModel
import com.aivhich.zaryaui.BodyText
import com.aivhich.zaryaui.HeadlineText
import com.aivhich.zaryaui.OutlinedField
import com.aivhich.zaryaui.PointsPanel
import com.aivhich.zaryaui.SubText
import com.aivhich.zaryaui.SubheadingText
import com.aivhich.zaryaui.UsualPanel


@Composable
fun LevelItem(text: String, level:String, fill:Boolean) {
    Column(Modifier.fillMaxWidth()) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .zIndex(0f)
        ) {
            val strokeWidth = 12.dp
            val color = if (fill) Color.Green else Color(0xFF929292)
            val strokeWidthPx = density.run { strokeWidth.toPx() }
            val height = size.height
            drawLine(
                start = Offset(x = 32.dp.toPx(), y = 0f),
                end = Offset(x = 32.dp.toPx(), y = height),
                color = color,
                strokeWidth = strokeWidthPx,
            )
        }
        UsualPanel(
            background = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .zIndex(1f)
        ) {
            Column(Modifier.padding(15.dp, 20.dp)) {
                HeadlineText(text = text)
                Spacer(Modifier.height(5.dp))
                BodyText(text = level, color = Color.Gray)
            }
        }
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .zIndex(0f)
        ) {
            val strokeWidth = 12.dp
            val color = if (fill) Color.Green else Color(0xFF929292)
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
fun LessonItem(
    title: String, time: String, points: String, passed: Boolean,
    last:Boolean,
    mypoints: String,
    onClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        UsualPanel(
            background = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1f)
                .shadow(0.75.dp, RoundedCornerShape(20.dp))
                .clip(RoundedCornerShape(20.dp))
                .clickable { if(passed || last)onClick() }
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp, 10.dp)
                    .height(IntrinsicSize.Max)
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    SubheadingText(
                        text = title,
                        fontWeight = FontWeight.W600,
                        textAlign = TextAlign.Start
                    )
                    SubText(text = "$time мин.")
                }
                PointsPanel(
                    text = if (mypoints!="0") "${mypoints}/${points}" else points,
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
            val strokeWidth = 12.dp
            val color = if (passed) Color.Green else Color(0xFF929292)
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
fun CheckboxOption(text: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        Text(text)
    }
}

@Composable
fun RadioButtonOption(text: String, isSelected: Boolean, onSelect: () -> Unit) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onSelect,
        )
        Text(text)
    }
}

@Composable
fun LoadingPage(pd:PaddingValues){
    Column(
        modifier = Modifier
            .padding(pd)
            .padding(10.dp, 15.dp, 10.dp, 5.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun FormComponent(
    answers: List<String>,
    choosed: List<String>,
    vm: LessonViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .then(modifier)
    ) {
        SubText(text = "Выбор нескольких вариантов ответа:")
        answers.forEachIndexed { index, s ->
            CheckboxOption(text = s, checked = choosed.contains(s)) {
                vm.updateAnswers(s)
            }
        }
    }
}

@Composable
fun CalcComponent(
    value: String,
    vm: LessonViewModel,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        OutlinedField(
            value = value,
            onChange = { it -> vm.setAnswers(it) },
            label = "Ответ на задачу",
            error = "",
            placeholder = "Введите ответ на задачу",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

@Composable
fun YesFormComponent(
    value: String,
    vm: LessonViewModel,
    modifier: Modifier = Modifier
) {
    RadioFormComponent(
        answers = listOf("Да", "Нет"),
        choosed = value,
        vm = vm,
        modifier = modifier
    )
}

@Composable
fun RadioFormComponent(
    answers: List<String>,
    choosed: String,
    vm: LessonViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .then(modifier)
    ) {
        SubText(text = "Выбор нескольких вариантов ответа:")
        answers.forEach { el ->
            RadioButtonOption(text = el, isSelected = (el == choosed)) {
                vm.setAnswers(el)
            }
        }
    }
}

@Composable
fun HtmlText(html: String? = "", modifier: Modifier = Modifier) {
    val htmlContent = """
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="utf-8">
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/katex@0.16.11/dist/katex.min.css" integrity="sha384-nB0miv6/jRmo5UMMR1wu3Gz6NLsoTkbqJghGIsx//Rlm+ZU03BU6SQNC66uf4l5+" crossorigin="anonymous">
        
            <!-- The loading of KaTeX is deferred to speed up page rendering -->
            <script defer src="https://cdn.jsdelivr.net/npm/katex@0.16.11/dist/katex.min.js" integrity="sha384-7zkQWkzuo3B5mTepMUcHkMB5jZaolc2xDwL6VFqjFALcbeS9Ggm/Yr2r3Dy4lfFg" crossorigin="anonymous"></script>
        
            <!-- To automatically render math in text elements, include the auto-render extension: -->
            <script defer src="https://cdn.jsdelivr.net/npm/katex@0.16.11/dist/contrib/auto-render.min.js" integrity="sha384-43gviWU0YVjaDtb/GhzOouOXtZMP/7XUzwPTstBeZFe/+rCMvRwr4yROQP43s0Xk" crossorigin="anonymous"
                onload="renderMathInElement(document.body);"></script>
        </head>
        <body style="padding-right:10px; padding-left:10px;">
        ${html ?: ""}
        </body>
        </html>
    """.trimIndent()

    AndroidView(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .then(modifier),
        factory = { context -> WebView(context) },
        update = { webView ->
            webView.setBackgroundColor(Color.Transparent.toArgb())
            webView.loadData(htmlContent, "text/html", "UTF-8")
        }
    )
}