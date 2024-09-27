package com.aivhich.physics.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aivhich.physics.common.Result
import com.aivhich.physics.common.UIStatus
import com.aivhich.physics.data.remote.dto.LessonDto
import com.aivhich.physics.data.remote.dto.LevelDto
import com.aivhich.physics.data.remote.dto.ProgressDto
import com.aivhich.physics.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(userUseCase: UserUseCase) : ViewModel() {
    private val userUseCase = userUseCase
    private val _uistate = MutableLiveData<UIStatus>(UIStatus.LOADING)
    val uistate: LiveData<UIStatus> get() = _uistate
    private fun setUIState(uiStatus: UIStatus) {
        _uistate.value = uiStatus
    }

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

    private val _progressPassed = MutableLiveData<List<ProgressDto>>(listOf())
    val progressPassed: LiveData<List<ProgressDto>> get() = _progressPassed
    private fun setProgress(progressPassed: List<ProgressDto>) {
        _progressPassed.value = progressPassed
    }

    init {
        initModel()
    }

    fun initModel() {
        viewModelScope.launch {
            when (val out = userUseCase.getUserCourseUseCase.invoke()) {
                is Result.Success -> {
                    setCourse(out.data.sortedBy { it.stepId })
                }

                is Result.Error -> {}
            }
            when (val out = userUseCase.getWeekPointsUseCase.invoke()) {
                is Result.Success -> {
                    setPoints(out.data)
                }

                is Result.Error -> {}
            }
            when (val out = userUseCase.getPassedLessonsUseCase.invoke()) {
                is Result.Success -> {
                    setPassed(out.data.passed)
                    setLastPassed(out.data.LastPassedId)
                    setProgress(out.data.lessonProgressSet.toList())
                    setLastPassed(
                        out.data.lessonProgressSet.filter { a ->
                            ((out.data.passed.filter { b -> b.id == a.lesson })
                                .map { m ->
                                    m.pages.sumOf { n -> n.points }
                                }.toList().sum()) <= a.points
                        }.count().toLong()
                    )
                    setUIState(UIStatus.PRESENT)
                }

                is Result.Error -> {}
            }
        }
    }
}