package com.melyseev.seasonticket72.data.user

import androidx.room.*

@Dao
interface UserDao {

    @Delete
    suspend fun delete(entity: UserEntity)

    @Insert
    suspend fun insert(entity: UserEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entity: UserEntity)

    @Query("SELECT * from ${UserEntity.TABLE_USER}")
    suspend fun getAll(): List<UserEntity>

    @Query("SELECT * from ${UserEntity.TABLE_USER} where  `id` = :id ")
    suspend fun getUserNameSurnameById(id: Long): UserEntity
}