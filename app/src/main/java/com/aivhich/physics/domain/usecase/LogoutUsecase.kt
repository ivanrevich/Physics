package com.aivhich.physics.domain.usecase

import com.aivhich.physics.domain.repository.TokenRepository
import com.aivhich.physics.domain.repository.UserRepository
import javax.inject.Inject

class LogoutUsecase @Inject
constructor(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke() {
        userRepository.delete()
        tokenRepository.remove()
    }
}
