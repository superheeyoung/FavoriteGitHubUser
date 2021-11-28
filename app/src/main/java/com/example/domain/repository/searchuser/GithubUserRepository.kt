package com.example.domain.repository.searchuser

import com.example.domain.model.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface GithubUserRepository {
    fun searchGithubUser(userName: String): Single<List<GithubUser>>
    fun insert(githubUser: GithubUser): Completable
    fun getAllGithubUser(): Single<List<GithubUser>>
    fun deleteItem(name: String): Completable
}