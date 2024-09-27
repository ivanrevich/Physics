package com.aivhich.physics.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aivhich.physics.common.Result
import com.aivhich.physics.common.UIStatus
import com.aivhich.physics.data.remote.dto.UserDto
import com.aivhich.physics.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class LeadersViewModel @Inject constructor(userUseCase: UserUseCase): ViewModel() {
    private val userUseCase = userUseCase;
    private val _uistate = MutableLiveData<UIStatus>(UIStatus.LOADING)
    val uistate: LiveData<UIStatus> get() = _uistate
    private fun setUIState(uiStatus: UIStatus) {
        _uistate.value = uiStatus
    }

    private val _users = MutableLiveData<List<UserDto>>(listOf())
    val users: LiveData<List<UserDto>> get() = _users
    private fun setUsers(users: List<UserDto>) {
        _users.value = users
    }

    private val _userPoints = MutableLiveData<Map<UUID, Long>>(mapOf())
    val userPoints: LiveData<Map<UUID, Long>> get() = _userPoints
    private fun setUserPoints(userPoints: Map<UUID, Long>) {
        _userPoints.value = userPoints
    }

    private val _place = MutableLiveData<Int>(1)
    val place: LiveData<Int> get() = _place
    private fun setPlace(place: Int) {
        _place.value = place
    }

    private val _points = MutableLiveData<Long>(0)
    val points: LiveData<Long> get() = _points
    private fun setPoints(points: Long) {
        _points.value = points
    }

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> get() = _name
    private fun setName(name:String) {
        _name.value = name
    }

    init{
        initModel()
    }


    fun initModel(){
        viewModelScope.launch {
            when(val out = userUseCase.getLeaders()){
                is Result.Success->{
                    setUsers(out.data.users)
                    setPoints(out.data.points)
                    setPlace(out.data.place)
                    setUserPoints(out.data.userPoints)
                }
                is Result.Error->{}
            }
            when(val out = userUseCase.getUserDataUseCase()){
                is Result.Success->{
                    setName("${out.data.name} ${out.data.surname}")
                    setUIState(UIStatus.PRESENT)
                }
                is Result.Error->{

                }
            }
        }
    }
}