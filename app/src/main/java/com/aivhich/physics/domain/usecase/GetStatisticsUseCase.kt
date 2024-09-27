package com.aivhich.physics.domain.usecase

import com.aivhich.physics.common.Result
import com.aivhich.physics.data.remote.dto.LeaderUsersResponse
import com.aivhich.physics.data.remote.dto.LessonDto
import com.aivhich.physics.data.remote.dto.StatisticsResponse
import com.aivhich.physics.data.remote.dto.UserDto
import com.aivhich.physics.domain.repository.LessonRepository
import com.aivhich.physics.domain.repository.TokenRepository
import com.aivhich.physics.domain.repository.UserRepository
import java.util.UUID
import javax.inject.Inject

class GetStatisticsUseCase @Inject
constructor(
    private val tokenRepository: TokenRepository,
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(): Result<StatisticsResponse> {
        return when(val answer = tokenRepository.get()){
            is Result.Success->{
                userRepository.getStatistics("Bearer ${answer.data.accesssToken}")
            }

            is Result.Error ->{
                Result.Error(Exception())
            }
        }
    }
}
