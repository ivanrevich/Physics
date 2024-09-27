package com.aivhich.physics.domain.repository

import com.aivhich.physics.common.Result
import com.aivhich.physics.data.remote.dto.CourseDto
import com.aivhich.physics.data.remote.dto.LeaderUsersResponse
import com.aivhich.physics.data.remote.dto.LessonDto
import com.aivhich.physics.data.remote.dto.LevelDto
import com.aivhich.physics.data.remote.dto.UpdateProgress
import com.aivhich.physics.data.remote.dto.UserDto
import java.util.UUID

interface LessonRepository {
    suspend fun getLesson(token: String, id: UUID):Result<LessonDto>
    suspend fun getCourse(token: String): Result<List<LevelDto>>
    suspend fun updateProgress(token:String, updateProgress: UpdateProgress):Result<Long>
    suspend fun getWeekPoints(token: String):Result<Long>
    suspend fun passedLesson(token: String):Result<CourseDto>
    suspend fun getLeaders(token:String):Result<LeaderUsersResponse>
}