package com.aivhich.physics.presentation.vm

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aivhich.physics.common.Result
import com.aivhich.physics.data.remote.dto.StatisticsResponse
import com.aivhich.physics.domain.model.User
import com.aivhich.physics.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val userUseCase: UserUseCase): ViewModel()  {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> get() = _name
    private fun setName(name:String) {
        _name.value = name
    }

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email
    private fun setEmail(email:String) {
        _email.value = email
    }

    private val _nickname = MutableLiveData<String>()
    val nickname: LiveData<String> get() = _nickname
    private fun setNickname(nickname:String) {
        _nickname.value = nickname
    }

    init {
        initModel()
    }

    private val _stats = MutableLiveData<StatisticsResponse>()
    val stats: LiveData<StatisticsResponse> get() = _stats
    private fun setStats(statisticsResponse: StatisticsResponse) {
        _stats.value = statisticsResponse
    }


    fun initModel() {
        viewModelScope.launch {
            when(val userdataReq: Result<User> = userUseCase.getUserDataUseCase()){
                is Result.Success->{
                    setName("${userdataReq.data.name} ${userdataReq.data.surname}")
                    setEmail(userdataReq.data.email)
                    setNickname(userdataReq.data.nickname)
                }
                is Result.Error->{ }
            }
            when(val answer = userUseCase.getStatisticsUseCase()){
                is Result.Success->{
                    setStats(answer.data)
                }
                is Result.Error->{

                }
            }
        }
    }

    fun logOut(activity: Activity?) {
        viewModelScope.launch {
            userUseCase.logoutUsecase()
            activity?.finishAffinity()
            activity?.finishAndRemoveTask()
        }
    }
}