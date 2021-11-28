package com.example.data.repository.favoriteuser

import com.example.data.entity.FavoriteGithubUserEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface FavoriteGithubUserDataSource {
    fun insert(githubUserEntity: FavoriteGithubUserEntity): Completable
    fun getAllGithubUser(): Single<List<FavoriteGithubUserEntity>>
    fun deleteItem(name: String): Completable
}