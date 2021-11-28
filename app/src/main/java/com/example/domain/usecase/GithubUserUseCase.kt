package com.example.domain.usecase

import com.example.domain.model.GithubUser
import com.example.domain.repository.searchuser.GithubUserRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class GithubUserUseCase(private val githubUserRepository: GithubUserRepository) {
    fun searchGithubUser(userName: String): Single<List<GithubUser>> {
        return githubUserRepository.searchGithubUser(userName)
            .toFlowable()
            .flatMapIterable { list -> list }
            .toSortedList { githubUser, githubUser2 ->
                githubUser.name.compareTo(githubUser2.name)
            }
    }

    fun insert(githubUser: GithubUser): Completable {
        return githubUserRepository.insert(githubUser)
    }

    fun getAllGithubUser(): Single<List<GithubUser>> {
        return githubUserRepository.getAllGithubUser()
            .toFlowable()
            .flatMapIterable { list -> list }
            .toSortedList { githubUser, githubUser2 ->
                githubUser.name.compareTo(githubUser2.name)
            }
    }

    fun deleteItem(name: String): Completable {
        return githubUserRepository.deleteItem(name)
    }
}