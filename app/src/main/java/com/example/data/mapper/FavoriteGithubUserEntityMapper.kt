package com.example.data.mapper

import com.example.data.entity.FavoriteGithubUserEntity
import com.example.domain.model.GithubUser

class FavoriteGithubUserEntityMapper {
    fun transform(target: List<FavoriteGithubUserEntity>): List<GithubUser> = with(target) {
        return map {
            GithubUser(it.avatarUrl, it.name, it.isChecked)
        }
    }

    fun transform(target: GithubUser): FavoriteGithubUserEntity = with(target) {
        return FavoriteGithubUserEntity(0, avatarUrl, name, isChecked)
    }
}