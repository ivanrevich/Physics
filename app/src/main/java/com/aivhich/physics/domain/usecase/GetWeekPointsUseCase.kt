package com.aivhich.physics.domain.usecase

import com.aivhich.physics.common.Result
import com.aivhich.physics.data.remote.dto.LessonDto
import com.aivhich.physics.domain.repository.LessonRepository
import com.aivhich.physics.domain.repository.TokenRepository
import java.util.UUID
import javax.inject.Inject

class GetWeekPointsUseCase @Inject
constructor(
    private val tokenRepository: TokenRepository,
    private val lessonRepository: LessonRepository
) {
    suspend operator fun invoke(): Result<Long> {
        return when(val answer = tokenRepository.get()){
            is Result.Success->{
                lessonRepository.getWeekPoints(token = answer.data.accesssToken)
            }

            is Result.Error ->{
                Result.Error(Exception())
            }
        }
    }
}
