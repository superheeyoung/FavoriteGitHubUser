package com.example.data.dto

import com.example.data.entity.GithubUserEntity
import com.google.gson.annotations.SerializedName

data class GithubUserDto(@SerializedName("items") val items : List<GithubUserEntity>)