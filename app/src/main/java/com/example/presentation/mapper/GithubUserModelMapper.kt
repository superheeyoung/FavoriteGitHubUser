package com.example.presentation.mapper

import com.example.domain.model.GithubUser
import com.example.presentation.model.GithubUserModel

class GithubUserModelMapper {
    fun transform(target : List<GithubUser>) : List<GithubUserModel> = with(target) {
        return map {
            GithubUserModel(it.avatarUrl, it.name, it.isChecked)
        }
    }

    fun transform(target : GithubUserModel) : GithubUser = with(target) {
        return GithubUser(avatarUrl, name, isCheck)
    }
}