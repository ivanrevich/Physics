package com.aivhich.physics.presentation.screens

import android.view.HapticFeedbackConstants
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aivhich.physics.common.AnswerCheck
import com.aivhich.physics.data.remote.dto.InfoType
import com.aivhich.physics.data.remote.dto.PageDto
import com.aivhich.physics.presentation.custom_components.FinalResultCard
import com.aivhich.physics.presentation.custom_components.TaskCard
import com.aivhich.physics.presentation.custom_components.TheoryCard
import com.aivhich.physics.presentation.vm.LessonViewModel
import com.aivhich.zaryaui.BodyText
import com.aivhich.zaryaui.MainButton
import com.aivhich.zaryaui.UsualProgressBar
import java.util.UUID


@Composable
fun LessonScreen(id: String, vm: LessonViewModel = hiltViewModel(), backToMain: () -> Unit) {
    vm.init(UUID.fromString(id))

    val lesson by vm.lesson.observeAsState()
    val index by vm.pageIdx.observeAsState()
    val page: PageDto? = lesson?.pages?.get(index ?: 0)
    val isResult by vm.isResult.observeAsState()
    val status by vm.isCorrectAnswer.observeAsState()
    val view = LocalView.current


    Scaffold(topBar = {
        UsualProgressBar(
            progress = if (isResult == false) {
                val stepId: Float = page?.stepId?.toFloat() ?: 0F
                (stepId) / (lesson?.pages?.size?.toFloat() ?: 1F)
            } else {
                1F
            }
        )
    },
        bottomBar = {
            Column(Modifier.padding(10.dp, 20.dp)) {
                if (page != null) {
                    if (page.infoType == InfoType.THEORY) {
                        MainButton(
                            backgroundColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier
                                .shadow(1.5.dp, clip = true, shape = RoundedCornerShape(25))
                                .fillMaxWidth(1f)
                                .height(48.dp),
                            onClick = {
                                if (isResult == false) {
                                    vm.check()
                                } else {
                                    backToMain()
                                }
                            }
                        ) {
                            BodyText(text = "Далее")
                        }
                    } else {
                        when (status) {
                            AnswerCheck.NOT_CHECKED -> {
                                MainButton(
                                    backgroundColor = MaterialTheme.colorScheme.secondary,
                                    contentColor = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier
                                        .shadow(1.5.dp, clip = true, shape = RoundedCornerShape(25))
                                        .fillMaxWidth(1f)
                                        .height(48.dp),
                                    onClick = {
                                        vm.check()
                                    }
                                ) {
                                    BodyText(text = "Проверить")
                                }
                            }

                            AnswerCheck.NOT_CORRECT -> {
                                view.performHapticFeedback(HapticFeedbackConstants.REJECT)
                                MainButton(
                                    backgroundColor = Color(0xFFE53935),
                                    contentColor = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier
                                        .shadow(1.5.dp, clip = true, shape = RoundedCornerShape(25))
                                        .fillMaxWidth(1f)
                                        .height(48.dp),
                                    onClick = {
                                        if (isResult == false) {
                                            vm.nextPage()
                                        } else {
                                            backToMain()
                                        }
                                    }
                                ) {
                                    BodyText(text = "Далее")
                                }
                            }

                            AnswerCheck.IS_CORRECT -> {
                                view.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
                                MainButton(
                                    backgroundColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier
                                        .shadow(1.5.dp, clip = true, shape = RoundedCornerShape(25))
                                        .fillMaxWidth(1f)
                                        .height(48.dp),
                                    onClick = {
                                        if (isResult == false) {
                                            vm.nextPage()
                                        } else {
                                            backToMain()
                                        }
                                    }
                                ) {
                                    BodyText(text = "Далее")
                                }
                            }

                            null -> TODO()
                        }
                    }
                }
            }
        }
    ) { pd ->
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(pd)
            ) {
                if (page != null) {
                    if (isResult == false) {
                        if (page.infoType == InfoType.THEORY) {
                            TheoryCard(html = page.texts)
                        } else {
                            TaskCard(html = page.texts, vm = vm, pageDto = page)
                        }
                    } else {
                        FinalResultCard(
                            result = "${vm.points}/${lesson!!.pages.sumOf { it.points ?: 0 }}",
                            success = (vm.points > (lesson!!.pages.sumOf { it.points ?: 0 }) * 0.5)
                        )
                    }
                }
            }
        }
    }
}