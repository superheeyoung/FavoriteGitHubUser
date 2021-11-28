package com.example.data.repository.favoriteuser

import com.example.data.database.FavoriteGithubUserDao
import com.example.data.entity.FavoriteGithubUserEntity

class FavoriteGithubUserCacheSourceImpl(private val favoriteGithubUserDao: FavoriteGithubUserDao) :
    FavoriteGithubUserCacheSource {
    override fun insert(favoriteGithubUserEntity: FavoriteGithubUserEntity) {
        return favoriteGithubUserDao.insert(favoriteGithubUserEntity)
    }

    override fun getAllGithubUser(): List<FavoriteGithubUserEntity> {
        return favoriteGithubUserDao.getAllGithubUser()
    }

    override fun deleteItem(name: String) {
        return favoriteGithubUserDao.deleteItem(name)
    }

}