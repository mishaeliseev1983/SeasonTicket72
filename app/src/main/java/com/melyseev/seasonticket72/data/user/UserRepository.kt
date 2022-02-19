package com.melyseev.seasonticket72.data.user

import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    //var selectedUser: UserEntity? = null

    suspend fun addNewUser(userEntity: UserEntity) {
        userDao.insert(userEntity)
    }

    suspend fun fetchUserList(): List<UserEntity> =
        userDao.getAll()

    suspend fun getUserNameSurnameById(id: Long): UserEntity =
        userDao.getUserNameSurnameById(id)
}