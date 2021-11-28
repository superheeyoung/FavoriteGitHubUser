package com.example.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteGithubUserENTITY")
data class FavoriteGithubUserEntity(
    @PrimaryKey(autoGenerate = true) var fId: Int = 0,
    @ColumnInfo(name = "avatarUrl") val avatarUrl: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "isChecked") val isChecked: Boolean
)