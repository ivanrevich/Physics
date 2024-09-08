package com.aivhich.physics.data.remote.retrofit

import com.aivhich.passport.data.remote.dto.response.AuthenticationResponse
import com.aivhich.physics.data.remote.dto.CourseDto
import com.aivhich.physics.data.remote.dto.LessonDto
import com.aivhich.physics.data.remote.dto.LevelDto
import com.aivhich.physics.data.remote.dto.UpdateProgress
import com.aivhich.physics.data.remote.dto.UserDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.UUID


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

    @GET("/api/v1/lessons/get")
    @Headers("Content-Type: application/json")
    suspend fun getLesson(
        @Header("Authorization") authorization: String,
        @Query("id") id: UUID
    ): LessonDto

    @GET("/api/v1/users/passed-lessons")
    @Headers("Content-Type: application/json")
    suspend fun passedLessons(@Header("Authorization") authorization: String): CourseDto


    @POST("/api/v1/lessons/updateProgress")
    @Headers("Content-Type: application/json")
    suspend fun updateProgress(
        @Header("Authorization") authorization: String,
        @Body updateProgress: UpdateProgress): Long

    @GET("/api/v1/lessons/weekPoints")
    @Headers("Content-Type: application/json")
    suspend fun getWeekPoints(
        @Header("Authorization") authorization: String,
    ):Long
}