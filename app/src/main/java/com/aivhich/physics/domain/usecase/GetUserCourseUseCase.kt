package com.aivhich.physics.domain.usecase

import com.aivhich.physics.common.Result
import com.aivhich.physics.data.remote.dto.LevelDto
import com.aivhich.physics.domain.repository.TokenRepository
import com.aivhich.physics.domain.repository.UserRepository
import javax.inject.Inject

class GetUserCourseUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository,
) {
    suspend operator fun invoke(): Result<List<LevelDto>> {
        return when(val answer = tokenRepository.get()){
            is Result.Success->{
                userRepository.getCourse(answer.data.accesssToken)
            }

            is Result.Error ->{
                Result.Error(Exception())
            }
        }
    }

}