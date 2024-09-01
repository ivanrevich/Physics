package com.aivhich.physics.data.repository


import com.aivhich.physics.common.Result
import com.aivhich.physics.data.datasource.UserDao
import com.aivhich.physics.data.remote.dto.LevelDto
import com.aivhich.physics.data.remote.dto.toUser
import com.aivhich.physics.data.remote.retrofit.ApiService
import com.aivhich.physics.domain.model.User
import com.aivhich.physics.domain.repository.UserRepository
import java.time.ZoneId
import java.time.ZonedDateTime
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dao: UserDao,
    private val api: ApiService
) : UserRepository {
    override suspend fun save(u: User) {
        dao.saveUser(u)
    }

    override suspend fun get(token: String): Result<User> {
        try {
            var user:User? = dao.get()
            if(user==null || user.timestamp + 30 < ZonedDateTime.now(ZoneId.of("UTC-0")).toEpochSecond()){
                dao.delete()
                user = api.getUserData("Bearer $token")?.toUser()
                dao.saveUser(user!!);
            }
            return Result.Success(user)
        }catch (e:Throwable){
            return Result.Error(e)
        }
    }

    override suspend fun getCourse(token: String): Result<List<LevelDto>> {
        try {
            var out = api.getLvlOfCourse("Bearer $token")
            return Result.Success(out)
        }catch (e:Throwable){
            return Result.Error(e)
        }
    }


    override suspend fun delete() {
        dao.delete()
    }

}