package com.example.presentation.viewModel

import com.example.domain.usecase.GithubUserUseCase
import com.example.presentation.base.BaseViewModel
import com.example.presentation.extension.SingleLiveEvent
import com.example.presentation.extension.onIOforMainThread
import com.example.presentation.mapper.GithubUserModelMapper
import com.example.presentation.model.GithubUserModel
import com.example.presentation.extension.Result
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy

class GithubUserMainViewModel(
    private val githubUserUseCase: GithubUserUseCase,
    private val githubUserModelMapper: GithubUserModelMapper
) : BaseViewModel() {

    var searchResultEvent = SingleLiveEvent<Result<List<GithubUserModel>>>()
    var favoriteUserEvent = SingleLiveEvent<Result<List<GithubUserModel>>>()

    fun searchGithubUser(userName: String) {
        disposable +=
            githubUserUseCase.searchGithubUser(userName)
                .map(githubUserModelMapper::transform)
                .onIOforMainThread()
                .doOnSubscribe {
                    searchResultEvent.value = Result.Loading
                }
                .subscribeBy(
                    onSuccess = {
                        searchResultEvent.value = Result.Success(it)
                    },
                    onError = {
                        searchResultEvent.value = Result.Error(it)
                    }
                )
    }

    fun insertFavoriteUser(item: GithubUserModel) {
        disposable +=
            githubUserUseCase.insert(githubUserModelMapper.transform(item.copy(isCheck = true)))
                .onIOforMainThread()
                .subscribeBy {}

    }

    fun favoriteUser() {
        disposable +=
            githubUserUseCase.getAllGithubUser()
                .map(githubUserModelMapper::transform)
                .onIOforMainThread()
                .doOnSubscribe {
                    favoriteUserEvent.value = Result.Loading
                }
                .subscribeBy(
                    onSuccess = {
                        favoriteUserEvent.value = Result.Success(it)
                    },
                    onError = {
                        favoriteUserEvent.value = Result.Error(it)
                    }
                )
    }

    fun deleteItem(name: String, isFavoriteView: Boolean = false) {
        disposable +=
            githubUserUseCase.deleteItem(name)
                .onIOforMainThread()
                .subscribeBy(
                    onComplete = {
                        if (isFavoriteView) favoriteUser()

                    }
                )
    }
}