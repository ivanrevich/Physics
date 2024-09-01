package com.aivhich.physics.di

import android.app.Application
import androidx.room.Room
import com.aivhich.physics.data.datasource.TokenDatabase
import com.aivhich.physics.data.datasource.UserDatabase
import com.aivhich.physics.data.remote.retrofit.ApiService
import com.aivhich.physics.data.remote.retrofit.Common
import com.aivhich.physics.data.repository.TokenRepositoryImpl
import com.aivhich.physics.data.repository.UserRepositoryImpl
import com.aivhich.physics.domain.repository.TokenRepository
import com.aivhich.physics.domain.repository.UserRepository
import com.aivhich.physics.domain.usecase.GetUserCourseUseCase
import com.aivhich.physics.domain.usecase.GetUserDataUseCase
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
    fun provideApi(): ApiService {
        return Common.retrofitService
    }

    @Provides
    @Singleton
    fun provideUserUseCase(repository: UserRepository,
                           tokenRepository: TokenRepository,
                           api: ApiService,
                           tokendb:TokenDatabase,
                           userdb: UserDatabase
    ): UserUseCase {
        return UserUseCase(
            getUserDataUseCase = GetUserDataUseCase(
                userRepository = repository,
                tokenRepository = tokenRepository),
            getUserCourseUseCase = GetUserCourseUseCase(
                userRepository = repository,
                tokenRepository = tokenRepository)
        )
    }
}