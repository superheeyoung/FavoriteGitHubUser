package com.example.data.repository.searchuser

import com.example.data.entity.GithubUserEntity
import io.reactivex.rxjava3.core.Single

interface GithubUserDataSource {
    fun searchGithubUser(userName: String): Single<List<GithubUserEntity>>
}