package com.example.data.api

import com.example.data.dto.GithubUserDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    @GET("/search/users")
    fun getGithubUser(
        @Query("q") name: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 100
    ): Single<GithubUserDto>
}