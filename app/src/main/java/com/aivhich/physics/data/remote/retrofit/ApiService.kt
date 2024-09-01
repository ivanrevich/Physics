package com.aivhich.physics.data.remote.retrofit

import com.aivhich.passport.data.remote.dto.response.AuthenticationResponse
import com.aivhich.physics.data.remote.dto.LevelDto
import com.aivhich.physics.data.remote.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST


interface ApiService {
    @GET("/api/v1/users/get")
    @Headers("Content-Type: application/json")
    suspend fun getUserData(@Header("Authorization") authorization: String): UserDto?

    @POST("/api/v1/auth/refresh-token")
    @Headers("Content-Type: application/json")
    suspend fun refreshToken(@Header("Authorization") authorization: String): AuthenticationResponse

    @GET("/api/v1/lvl")
    @Headers("Content-Type: application/json")
    suspend fun getLvlOfCourse(@Header("Authorization") authorization: String): List<LevelDto>
}