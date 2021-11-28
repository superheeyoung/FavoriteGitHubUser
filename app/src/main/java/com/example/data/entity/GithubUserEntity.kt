package com.example.data.entity

import com.google.gson.annotations.SerializedName

data class GithubUserEntity(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("login")
    val name: String,
    val isChecked: Boolean = false
)