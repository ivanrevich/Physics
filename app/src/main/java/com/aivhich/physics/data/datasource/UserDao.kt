package com.aivhich.physics.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aivhich.physics.domain.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    suspend fun get(): User

    @Query("DELETE FROM user")
    suspend fun delete()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: User)
}