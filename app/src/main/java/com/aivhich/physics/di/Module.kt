package com.aivhich.physics.di

import android.app.Application
import androidx.room.Room
import com.aivhich.physics.data.datasource.TokenDatabase
import com.aivhich.physics.data.datasource.UserDatabase
import com.aivhich.physics.data.remote.retrofit.ApiService
import com.aivhich.physics.data.remote.retrofit.Common
import com.aivhich.physics.data.repository.LessonRepositoryImpl
import com.aivhich.physics.data.repository.TokenRepositoryImpl
import com.aivhich.physics.data.repository.UserRepositoryImpl
import com.aivhich.physics.domain.repository.LessonRepository
import com.aivhich.physics.domain.repository.TokenRepository
import com.aivhich.physics.domain.repository.UserRepository
import com.aivhich.physics.domain.usecase.DeleteUserFromServerUseCase
import com.aivhich.physics.domain.usecase.GetLeadersUseCase
import com.aivhich.physics.domain.usecase.GetOneLessonUseCase
import com.aivhich.physics.domain.usecase.GetPassedLessonsUseCase
import com.aivhich.physics.domain.usecase.GetStatisticsUseCase
import com.aivhich.physics.domain.usecase.GetUserCourseUseCase
import com.aivhich.physics.domain.usecase.GetUserDataUseCase
import com.aivhich.physics.domain.usecase.GetWeekPointsUseCase
import com.aivhich.physics.domain.usecase.LogoutUsecase
import com.aivhich.physics.domain.usecase.UpdateProgressUseCase
import com.aivhich.physics.domain.usecase.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    @Singleton
    fun provideUserDatabase(app: Application): UserDatabase {
        return Room.databaseBuilder(
            app,
            UserDatabase::class.java,
            UserDatabase.NAME_DATABASE
        ).fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun provideUserRepository(db: UserDatabase, api: ApiService): UserRepository {
        return UserRepositoryImpl(dao = db.userDao, api = api)
    }

    @Provides
    @Singleton
    fun provideTokenDatabase(app: Application): TokenDatabase {
        return Room.databaseBuilder(
            app,
            TokenDatabase::class.java,
            TokenDatabase.NAME_DATABASE
        ).fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun provideTokenRepository(api: ApiService, db:TokenDatabase): TokenRepository {
        return TokenRepositoryImpl(dao=db.tokenDao, api = api)
    }

    @Provides
    @Singleton
    fun provideLessonRepository(api: ApiService): LessonRepository {
        return LessonRepositoryImpl(api = api)
    }



    @Provides
    @Singleton
    fun provideApi(): ApiService {
        return Common.retrofitService
    }

    @Provides
    @Singleton
    fun provideUserUseCase(repository: UserRepository,
                           tokenRepository: TokenRepository,
                           lessonRepository: LessonRepository,
                           api: ApiService,
                           tokendb:TokenDatabase,
                           userdb: UserDatabase
    ): UserUseCase {
        return UserUseCase(
            getUserDataUseCase = GetUserDataUseCase(
                userRepository = repository,
                tokenRepository = tokenRepository),
            getUserCourseUseCase = GetUserCourseUseCase(
                lessonRepository = lessonRepository,
                tokenRepository = tokenRepository),
            getOneLesson = GetOneLessonUseCase(
                lessonRepository = lessonRepository,
                tokenRepository = tokenRepository
            ),
            updateProgressUseCase = UpdateProgressUseCase(
                lessonRepository = lessonRepository,
                tokenRepository = tokenRepository
            ),
            getPassedLessonsUseCase = GetPassedLessonsUseCase(
                lessonRepository = lessonRepository,
                tokenRepository = tokenRepository
            ),
            getWeekPointsUseCase = GetWeekPointsUseCase(
                lessonRepository = lessonRepository,
                tokenRepository = tokenRepository
            ),
            getLeaders = GetLeadersUseCase(
                tokenRepository = tokenRepository,
                lessonRepository = lessonRepository
            ),
            deleteUserFromServerUseCase = DeleteUserFromServerUseCase(
                tokenRepository = tokenRepository,
                userRepository = repository
            ),
            logoutUsecase = LogoutUsecase(tokenRepository = tokenRepository,
                userRepository = repository),
            getStatisticsUseCase = GetStatisticsUseCase(tokenRepository = tokenRepository,
                userRepository = repository)
        )
    }
}