package com.aivhich.physics.data.repository

import com.aivhich.physics.common.Result
import com.aivhich.physics.data.remote.dto.CourseDto
import com.aivhich.physics.data.remote.dto.LeaderUsersResponse
import com.aivhich.physics.data.remote.dto.LessonDto
import com.aivhich.physics.data.remote.dto.LevelDto
import com.aivhich.physics.data.remote.dto.UpdateProgress
import com.aivhich.physics.data.remote.retrofit.ApiService
import com.aivhich.physics.domain.repository.LessonRepository
import java.util.UUID
import javax.inject.Inject

class LessonRepositoryImpl @Inject constructor(
    private val api: ApiService
):LessonRepository {
    override suspend fun getLesson(token: String, id:UUID): Result<LessonDto> {
        try {
            var out = api.getLesson("Bearer $token", id)
            return Result.Success(out)
        }catch (e:Throwable){
            return Result.Error(e)
        }
    }

    override suspend fun getCourse(token: String): Result<List<LevelDto>> {
        try {
            var out = api.getLvlOfCourse("Bearer $token")
            return Result.Success(out)
        }catch (e:Throwable){
            return Result.Error(e)
        }
    }

    override suspend fun updateProgress(
        token: String,
        updateProgress: UpdateProgress
    ): Result<Long> {
        return try {
            Result.Success(api.updateProgress("Bearer $token", updateProgress))
        }catch (e:Throwable){
            Result.Error(e)
        }
    }

    override suspend fun getWeekPoints(token: String): Result<Long> {
        return try {
            Result.Success(api.getWeekPoints("Bearer $token"))
        }catch (e:Throwable){
            Result.Error(e)
        }
    }

    override suspend fun passedLesson(token: String): Result<CourseDto> {
        return try {
            Result.Success(api.passedLessons("Bearer $token"))
        }catch (e:Throwable){
            Result.Error(e)
        }
    }

    override suspend fun getLeaders(token: String): Result<LeaderUsersResponse> {
        return try{
            Result.Success(api.getLeaders("Bearer $token"))
        }catch (e:Throwable){
            Result.Error(e)
        }
    }
}