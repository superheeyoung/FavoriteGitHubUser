package com.example.data.mapper

import com.example.data.entity.GithubUserEntity
import com.example.domain.model.GithubUser

class GithubUserEntityMapper {
    fun transform(target: List<GithubUserEntity>): List<GithubUser> = with(target) {
        return map {
            GithubUser(it.avatarUrl, it.name, it.isChecked)
        }
    }

    fun transform(target: GithubUser): GithubUserEntity = with(target) {
        return GithubUserEntity(avatarUrl, name, isChecked)
    }
}