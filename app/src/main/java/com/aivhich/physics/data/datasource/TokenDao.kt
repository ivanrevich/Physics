package com.aivhich.physics.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aivhich.physics.domain.model.Token

@Dao
interface TokenDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveToken(token: Token)

    @Query("SELECT * FROM token")
    suspend fun getToken(): Token


    @Query("DELETE FROM token")
    suspend fun deleteToken()
}