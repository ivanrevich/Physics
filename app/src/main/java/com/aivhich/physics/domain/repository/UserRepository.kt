package com.aivhich.physics.domain.repository

import com.aivhich.physics.common.Result
import com.aivhich.physics.data.remote.dto.LevelDto
import com.aivhich.physics.data.remote.dto.StatisticsResponse
import com.aivhich.physics.domain.model.User


interface UserRepository {
    suspend fun save(u: User)
    suspend fun get(token:String): Result<User>
    suspend fun delete()
    suspend fun deleteUserFromServer(token: String):Result<Boolean>
    suspend fun getStatistics(token: String):Result<StatisticsResponse>
}