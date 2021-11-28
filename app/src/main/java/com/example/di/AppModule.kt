package com.example.di

import androidx.room.Room
import com.example.data.GithubUserRepositoryImpl
import com.example.data.database.AppDatabase
import com.example.data.mapper.FavoriteGithubUserEntityMapper
import com.example.data.mapper.GithubUserEntityMapper
import com.example.data.repository.favoriteuser.FavoriteGithubUserCacheSource
import com.example.data.repository.favoriteuser.FavoriteGithubUserCacheSourceImpl
import com.example.data.repository.favoriteuser.FavoriteGithubUserDataSource
import com.example.data.repository.favoriteuser.FavoriteGithubUserDataSourceImpl
import com.example.data.repository.searchuser.GithubUserDataSource
import com.example.data.repository.searchuser.GithubUserDataSourceImpl
import com.example.data.repository.searchuser.GithubUserRemoteSource
import com.example.data.repository.searchuser.GithubUserRemoteSourceImpl
import com.example.domain.repository.searchuser.GithubUserRepository
import com.example.domain.usecase.GithubUserUseCase
import com.example.presentation.mapper.GithubUserModelMapper
import com.example.presentation.viewModel.GithubUserMainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val remoteSourceModule = module {
    single<GithubUserRemoteSource> { GithubUserRemoteSourceImpl(get()) }
}

val cacheSourceModule = module {
    single<FavoriteGithubUserCacheSource> { FavoriteGithubUserCacheSourceImpl(get()) }
}

val dataSourceModule = module {
    single<GithubUserDataSource> { GithubUserDataSourceImpl(get()) }
    single<FavoriteGithubUserDataSource> { FavoriteGithubUserDataSourceImpl(get()) }
}

val repositoryModule = module {
    single<GithubUserRepository> { GithubUserRepositoryImpl(get(), get(), get(), get()) }
}

val mapperModule = module {
    factory { GithubUserEntityMapper() }
    factory { GithubUserModelMapper() }
    factory { FavoriteGithubUserEntityMapper() }
}

val useCaseModule = module {
    factory { GithubUserUseCase(get()) }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "favorite-database").build()
    }
    single { get<AppDatabase>().favoriteGithubUserDao() }
}

val viewModelModule = module {
    viewModel { GithubUserMainViewModel(get(), get()) }
}

val appModule = listOf(
    retrofitModule,
    remoteSourceModule,
    dataSourceModule,
    repositoryModule,
    cacheSourceModule,
    mapperModule,
    useCaseModule,
    databaseModule,
    viewModelModule
)