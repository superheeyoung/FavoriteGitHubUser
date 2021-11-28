package com.example.data.repository.favoriteuser

import com.example.data.entity.FavoriteGithubUserEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class FavoriteGithubUserDataSourceImpl(private val favoriteGithubUserCacheSource: FavoriteGithubUserCacheSource) :
    FavoriteGithubUserDataSource {
    override fun insert(favoriteGithubUserEntity: FavoriteGithubUserEntity): Completable {
        return Completable.fromAction {
            favoriteGithubUserCacheSource.insert(
                favoriteGithubUserEntity
            )
        }
    }

    override fun getAllGithubUser(): Single<List<FavoriteGithubUserEntity>> {
        return Single.fromCallable { favoriteGithubUserCacheSource.getAllGithubUser() }
    }

    override fun deleteItem(name: String): Completable {
        return Completable.fromAction { favoriteGithubUserCacheSource.deleteItem(name) }
    }
}