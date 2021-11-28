package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.entity.FavoriteGithubUserEntity

@Database(entities = [FavoriteGithubUserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteGithubUserDao(): FavoriteGithubUserDao
}