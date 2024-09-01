package com.aivhich.physics.domain.repository


import com.aivhich.physics.common.Result
import com.aivhich.physics.domain.model.Token


interface TokenRepository {
    suspend fun save(token: Token)
    suspend fun get(): Result<Token>
    suspend fun remove()
    suspend fun refresh(): Result<Token>
}