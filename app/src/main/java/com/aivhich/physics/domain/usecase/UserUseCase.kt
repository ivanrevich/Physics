package com.aivhich.physics.domain.usecase

data class UserUseCase(
    val getUserDataUseCase: GetUserDataUseCase,
    val getUserCourseUseCase: GetUserCourseUseCase
)