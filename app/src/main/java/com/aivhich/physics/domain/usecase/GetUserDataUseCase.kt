package com.aivhich.physics.domain.usecase

import com.aivhich.physics.common.Result
import com.aivhich.physics.domain.model.User
import com.aivhich.physics.domain.repository.TokenRepository
import com.aivhich.physics.domain.repository.UserRepository
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository,
) {
    suspend operator fun invoke():Result<User>{
        return when(val answer = tokenRepository.get()){
            is Result.Success->{
                userRepository.get(answer.data.accesssToken)
            }

            is Result.Error ->{
                Result.Error(Exception())
            }
        }
    }

}