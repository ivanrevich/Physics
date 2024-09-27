package com.aivhich.physics.presentation.vm

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aivhich.physics.common.Result
import com.aivhich.physics.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val userUseCase: UserUseCase): ViewModel()  {


    init {
        initModel()
    }


    fun initModel() {

    }

    fun deleteUserFromServer(activity: Activity?) {
        viewModelScope.launch {
            when(val answer: Result<Boolean> = userUseCase.deleteUserFromServerUseCase()){
                is Result.Success->{
                    userUseCase.logoutUsecase()
                    activity?.finishAffinity()
                    activity?.finishAndRemoveTask()
                }
                is Result.Error->{ }
            }
        }
    }
}