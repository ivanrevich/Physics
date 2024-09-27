package com.aivhich.physics.data.repository

import com.aivhich.physics.common.Result
import com.aivhich.physics.data.datasource.TokenDao
import com.aivhich.physics.data.remote.retrofit.ApiService
import com.aivhich.physics.domain.model.Token
import com.aivhich.physics.domain.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val dao: TokenDao,
    private val api:ApiService
) : TokenRepository {
    override suspend fun save(token: Token){
        dao.saveToken(token)
    }

    override suspend fun get(): Result<Token> {
        return try {
            Result.Success(dao.getToken())
        } catch (e: Exception) {
            try {
                refresh()
            } catch (e2: Exception) {
                Result.Error(e2)
            }
        }
    }

    override suspend fun remove() {
        dao.deleteToken()
    }

    override suspend fun refresh(): Result<Token> {
        try {
            val old = dao.getToken()
            val answer = api.refreshToken("Bearer "+old.refreshToken)
            val token = Token(0, answer.accessToken, answer.refreshToken)
            remove()
            save(token)
            return Result.Success(token)
        }catch (e:Exception){
            return Result.Error(e);
        }
    }
}