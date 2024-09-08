package com.aivhich.physics.presentation.custom_components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.aivhich.physics.common.AnswerCheck
import com.aivhich.physics.data.remote.dto.FormType
import com.aivhich.physics.data.remote.dto.PageDto
import com.aivhich.physics.presentation.vm.LessonViewModel
import com.aivhich.zaryaui.BodyText
import com.aivhich.zaryaui.MainButton
import com.aivhich.zaryaui.SubheadingText

@Composable
fun TaskCard(html: String?, vm: LessonViewModel, pageDto: PageDto) {
    val choosedItems by vm.answers.observeAsState()
    val mContext = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { contentPadding ->
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            HtmlText(
                html,
                Modifier
                    .weight(1f)
                    .verticalScroll(
                        rememberScrollState()
                    )
            )
            when (pageDto.form.formType) {
                FormType.CHOOSE_CORRECTS -> {
                    FormComponent(
                        answers = pageDto.form.answers.toList(),
                        choosed = choosedItems!!,
                        vm = vm,
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(
                                rememberScrollState()
                            )
                    )
                }

                FormType.CALC -> {
                    CalcComponent(
                        if (choosedItems!!.isNotEmpty()) choosedItems?.get(0) ?: "" else "",
                        vm,
                        Modifier.weight(1f)
                    )
                }

                FormType.YES_OR_NO -> {
                    YesFormComponent(
                        value = if (choosedItems?.isNotEmpty()==true)
                            choosedItems?.get(0) ?: " " else " ",
                        vm = vm,
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(
                                rememberScrollState()
                            )
                    )
                }

                FormType.CHOOSE_ONE -> {
                    RadioFormComponent(
                        answers = pageDto.form.answers.toList(),
                        choosed = if (choosedItems?.isNotEmpty()==true) choosedItems?.get(0)
                            ?: " " else " ",
                        vm = vm,
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(
                                rememberScrollState()
                            )
                    )
                }

                FormType.LINK_ITEMS -> {
                    //nothing
                }
            }
        }
    }
    LaunchedEffect(key1 = "Snack") {
        vm.message.collect {
            if (!it.hasBeenHandled) {
                snackbarHostState.showSnackbar(
                    it.getContentIfNotHandled()?.asString(mContext).toString()
                )
            }
        }
    }
}