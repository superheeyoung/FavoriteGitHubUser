package com.example.data.repository.searchuser

import com.example.data.api.GithubApi
import com.example.data.entity.GithubUserEntity
import io.reactivex.rxjava3.core.Single

class GithubUserRemoteSourceImpl(private val githubApi: GithubApi) : GithubUserRemoteSource {
    override fun searchGithubUser(userName: String): Single<List<GithubUserEntity>> {
        return githubApi.getGithubUser(userName).map {
            it.items
        }
    }
}