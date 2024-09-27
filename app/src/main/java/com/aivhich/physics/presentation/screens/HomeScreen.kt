package com.aivhich.physics.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aivhich.physics.R
import com.aivhich.physics.common.UIStatus
import com.aivhich.physics.data.remote.dto.LessonDto
import com.aivhich.physics.presentation.custom_components.LessonItem
import com.aivhich.physics.presentation.custom_components.LevelItem
import com.aivhich.physics.presentation.custom_components.LoadingPage
import com.aivhich.physics.presentation.vm.HomeViewModel
import com.aivhich.zaryaui.Appbar
import java.lang.Math.pow

@Composable
fun HomeScreen(nav: NavController, vm: HomeViewModel) {
    val course by vm.course.observeAsState()
    val points by vm.points.observeAsState()
    val progresses by vm.progressPassed.observeAsState()
    val last by vm.lastPassed.observeAsState()
    val passed by vm.passed.observeAsState()
    val uistate by vm.uistate.observeAsState(UIStatus.LOADING)
    val listState = remember { LazyListState() }

    LaunchedEffect(key1 = "Unit1") {
        vm.initModel()
    }
    LaunchedEffect(last) {
        try {
            val lessons: ArrayList<LessonDto> = arrayListOf();
            course?.forEach { it -> lessons.addAll(it.lessons) }
            listState.scrollToItem(lessons.get(last?.toInt()!!).stepId.toInt())
        }catch (e:Exception){

        }
    }

    Scaffold(topBar = {
        Appbar(
            allstrikes = points?.toInt() ?: 0,
            maxval = (pow(
                10.0,
                (2 * (points?.toInt() ?: 10)).toString().length.toDouble()
            ) / 2).toInt(),//max value in leader board should be here
            painter = painterResource(id = R.drawable.baseline_bolt_24)
        )
    }) { pd ->
        Surface {
            if (uistate == UIStatus.PRESENT) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(pd)
                ) {
                    LazyColumn(
                        state=listState,
                        modifier = Modifier.padding(10.dp, 0.dp)
                    ) {
                        course?.forEachIndexed { index, level ->
                            val prevs = course?.subList(0, index)?.map { it.lessons.count() }?.sum()
                            item(contentType = "header") {
                                LevelItem(
                                    text = level.title,
                                    level = "уровень ${level.stepId}",
                                    fill = ((last?.toInt() ?: 0) >= (prevs
                                        ?: 0)) || level.stepId == 1L
                                )
                            }
                            items(level.lessons.sortedBy { it.stepId }) { lesson ->
                                val points = (lesson.pages.sumOf { it.points }).toString();
                                val mypoints = progresses?.filter { lesson.id == it.lesson }
                                    ?.map { it.points }
                                    ?.sum() ?: 0L
                                if (prevs != null) {
                                    LessonItem(
                                        lesson.title,
                                        (lesson.pages.size * 2).toString(),
                                        points = points,
                                        last = (last?.plus(1)
                                            ?.toInt() == (prevs?.plus(lesson.stepId))?.toInt()),
                                        mypoints = mypoints.toString(),
                                        passed = (mypoints >= points.toLong() * 0.5) &&
                                                (passed?.map { it.id }
                                                    ?.contains(lesson.id) == true),
                                    ) {
                                        nav.navigate("lesson/${lesson.id}")
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                LoadingPage(pd = pd)
            }
        }
    }
}