package com.aivhich.physics.domain.usecase

import com.aivhich.physics.common.Result
import com.aivhich.physics.data.remote.dto.LessonDto
import com.aivhich.physics.data.remote.dto.UpdateProgress
import com.aivhich.physics.domain.repository.LessonRepository
import com.aivhich.physics.domain.repository.TokenRepository
import java.util.UUID
import javax.inject.Inject

class UpdateProgressUseCase @Inject
constructor(
    private val tokenRepository: TokenRepository,
    private val lessonRepository: LessonRepository
) {
    suspend operator fun invoke(updateProgress: UpdateProgress): Result<Long> {
        return when(val answer = tokenRepository.get()){
            is Result.Success->{
                lessonRepository.updateProgress(token = answer.data.accesssToken, updateProgress = updateProgress)
            }

            is Result.Error ->{
                Result.Error(Exception())
            }
        }
    }
}
