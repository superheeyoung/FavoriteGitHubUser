package com.example.data.repository.searchuser

import com.example.data.entity.GithubUserEntity
import io.reactivex.rxjava3.core.Single

class GithubUserDataSourceImpl(private val githubUserRemoteSource: GithubUserRemoteSource) :
    GithubUserDataSource {
    override fun searchGithubUser(userName: String): Single<List<GithubUserEntity>> {
        return githubUserRemoteSource.searchGithubUser(userName)
    }
}