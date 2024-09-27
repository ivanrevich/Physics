package com.aivhich.physics.domain.usecase

import com.aivhich.physics.common.Result
import com.aivhich.physics.data.remote.dto.LeaderUsersResponse
import com.aivhich.physics.domain.repository.LessonRepository
import com.aivhich.physics.domain.repository.TokenRepository
import com.aivhich.physics.domain.repository.UserRepository
import javax.inject.Inject

class DeleteUserFromServerUseCase @Inject
constructor(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(): Result<Boolean> {
        return when(val answer = tokenRepository.get()){
            is Result.Success->{
                userRepository.deleteUserFromServer("Bearer ${answer.data.accesssToken}")
            }

            is Result.Error ->{
                Result.Error(Exception())
            }
        }
    }
}
