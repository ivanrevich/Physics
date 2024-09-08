package com.aivhich.physics.presentation.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aivhich.physics.common.Result
import com.aivhich.physics.data.remote.dto.LessonDto
import com.aivhich.physics.data.remote.dto.LevelDto
import com.aivhich.physics.domain.usecase.GetUserCourseUseCase
import com.aivhich.physics.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(userUseCase: UserUseCase): ViewModel() {
    private val _course = MutableLiveData<List<LevelDto>>(listOf())
    val course: LiveData<List<LevelDto>> get() = _course
    private fun setCourse(course: List<LevelDto>) {
        _course.value = course
    }

    private val _points = MutableLiveData<Long>(0)
    val points: LiveData<Long> get() = _points
    private fun setPoints(points: Long) {
        _points.value = points
    }

    private val _lastPassed = MutableLiveData<Long>()
    val lastPassed: LiveData<Long> get() = _lastPassed
    private fun setLastPassed(lastPassed: Long) {
        _lastPassed.value = lastPassed
    }

    private val _passed = MutableLiveData<List<LessonDto>>(listOf())
    val passed: LiveData<List<LessonDto>> get() = _passed
    private fun setPassed(passed: List<LessonDto>) {
        _passed.value = passed
    }

    init{
        viewModelScope.launch {
            when(val out = userUseCase.getUserCourseUseCase.invoke()){
                is Result.Success->{
                    setCourse(out.data)
                }
                is Result.Error->{}
            }
            when(val out = userUseCase.getWeekPointsUseCase.invoke()){
                is Result.Success->{
                    setPoints(out.data)
                }
                is Result.Error->{}
            }
            when(val out = userUseCase.getPassedLessonsUseCase.invoke()){
                is Result.Success->{
                    setPassed(out.data.passed)
                    Log.d("out 2", out.data.passed.toString())
                    setLastPassed(out.data.LastPassedId)
                }
                is Result.Error->{}
            }
        }
    }
}