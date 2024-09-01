package com.aivhich.physics.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aivhich.physics.domain.model.User


@Database(
    entities = [User::class],
    version = 1,
)
abstract class UserDatabase: RoomDatabase(){
    abstract val userDao: UserDao

    companion object{
        const val NAME_DATABASE = "user"
    }
}