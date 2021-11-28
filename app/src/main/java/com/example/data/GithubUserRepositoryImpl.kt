package com.example.data

import com.example.data.mapper.FavoriteGithubUserEntityMapper
import com.example.data.mapper.GithubUserEntityMapper
import com.example.data.repository.favoriteuser.FavoriteGithubUserDataSource
import com.example.data.repository.searchuser.GithubUserDataSource
import com.example.domain.model.GithubUser
import com.example.domain.repository.searchuser.GithubUserRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class GithubUserRepositoryImpl(
    private val githubUserDataSource: GithubUserDataSource,
    private val favoriteGithubUserDataSource: FavoriteGithubUserDataSource,
    private val githubUserEntityMapper: GithubUserEntityMapper,
    private val favoriteGithubUserEntityMapper: FavoriteGithubUserEntityMapper
) : GithubUserRepository {
    override fun searchGithubUser(userName: String): Single<List<GithubUser>> {
        return githubUserDataSource.searchGithubUser(userName)
            .map(githubUserEntityMapper::transform)
    }

    override fun insert(githubUser: GithubUser): Completable {
        return favoriteGithubUserDataSource.insert(
            favoriteGithubUserEntityMapper.transform(
                githubUser
            )
        )
    }

    override fun getAllGithubUser(): Single<List<GithubUser>> {
        return favoriteGithubUserDataSource.getAllGithubUser()
            .map(favoriteGithubUserEntityMapper::transform)
    }

    override fun deleteItem(name: String): Completable {
        return favoriteGithubUserDataSource.deleteItem(name)
    }
}