package com.aivhich.physics.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aivhich.physics.common.Result
import com.aivhich.physics.data.remote.dto.LevelDto
import com.aivhich.physics.domain.usecase.GetUserCourseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(getUserCourseUseCase: GetUserCourseUseCase): ViewModel() {
    private val _course = MutableLiveData<List<LevelDto>>(listOf())
    val course: LiveData<List<LevelDto>> get() = _course
    private fun setCourse(course: List<LevelDto>) {
        _course.value = course
    }

    init{
        viewModelScope.launch {
            when(val out = getUserCourseUseCase.invoke()){
                is Result.Success->{
                    setCourse(out.data)
                }
                is Result.Error->{

                }
            }
        }
    }
}