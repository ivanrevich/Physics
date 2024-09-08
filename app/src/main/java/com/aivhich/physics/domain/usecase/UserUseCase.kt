package com.aivhich.physics.domain.usecase

data class UserUseCase(
    val getUserDataUseCase: GetUserDataUseCase,
    val getUserCourseUseCase: GetUserCourseUseCase,
    val getOneLesson:GetOneLessonUseCase,
    val updateProgressUseCase: UpdateProgressUseCase,
    val getPassedLessonsUseCase: GetPassedLessonsUseCase,
    val getWeekPointsUseCase: GetWeekPointsUseCase
)