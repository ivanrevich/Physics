package com.aivhich.physics.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.aivhich.physics.presentation.custom_components.TaskCard
import com.aivhich.physics.presentation.custom_components.TheoryCard
import com.aivhich.physics.presentation.vm.LessonViewModel
import com.aivhich.zaryaui.UsualProgressBar

@Composable
fun LessonScreen(id:String, vm: LessonViewModel = hiltViewModel()) {
    Scaffold(topBar = {
        UsualProgressBar(progress = 0.5f)
    }) { pd ->
        Surface {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(pd)
            ) {
                if(theory){
                    TheoryCard(html = "")
                }else{
                    TaskCard(html = "")
                }
            }
        }
    }
}