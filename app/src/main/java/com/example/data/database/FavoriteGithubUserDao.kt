package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.entity.FavoriteGithubUserEntity

@Dao
interface FavoriteGithubUserDao {
    @Insert
    fun insert(favoritegithubUserEntity: FavoriteGithubUserEntity)

    @Query("SELECT * from favoriteGithubUserENTITY")
    fun getAllGithubUser(): List<FavoriteGithubUserEntity>

    @Query("DELETE FROM favoriteGithubUserENTITY WHERE name = :name")
    fun deleteItem(name: String)
}