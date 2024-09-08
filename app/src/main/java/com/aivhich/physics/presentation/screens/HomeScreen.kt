package com.aivhich.physics.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aivhich.physics.R
import com.aivhich.physics.presentation.custom_components.LessonItem
import com.aivhich.physics.presentation.custom_components.LevelItem
import com.aivhich.physics.presentation.vm.HomeViewModel
import com.aivhich.zaryaui.Appbar

@Composable
fun HomeScreen(nav:NavController, vm:HomeViewModel= hiltViewModel()) {
    val course by vm.course.observeAsState()
    val points by vm.points.observeAsState()
    val lastPassed by vm.lastPassed.observeAsState()
    val passed by vm.passed.observeAsState()

    Scaffold(topBar = {
        Appbar(allstrikes = points?.toInt()?:0,
            maxval = 1000,//max value in leader board should be here
            painter = painterResource(id = R.drawable.baseline_bolt_24))
    }) { pd ->
        Surface {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(pd)
            ) {
                LazyColumn(
                    Modifier.padding(10.dp, 0.dp)
                ) {
                    course?.sortedBy { it.stepId }?.forEach { level ->
                        item(contentType = "header") {
                            LevelItem(
                                text = level.title
                            )
                        }
                        items(level.lessons.sortedBy { it.stepId }) { lesson ->
                            Log.d("out",
                                (passed?.map { it.id }).toString()+" "+lesson.id.toString()
                            )
                            LessonItem(lesson.title,
                                (lesson.pages.size*2).toString(),
                                (lesson.pages.sumOf { it.points }).toString(),
                                passed?.map { it.id }?.contains(lesson.id)==true
                            ) {
                                nav.navigate("lesson/${lesson.id}")
                            }
                        }
                    }
                }
            }
        }
    }
}