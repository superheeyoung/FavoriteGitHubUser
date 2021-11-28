package com.example.data.repository.favoriteuser

import com.example.data.entity.FavoriteGithubUserEntity

interface FavoriteGithubUserCacheSource {
    fun insert(favoriteGithubUserEntity: FavoriteGithubUserEntity)
    fun getAllGithubUser(): List<FavoriteGithubUserEntity>
    fun deleteItem(name: String)
}