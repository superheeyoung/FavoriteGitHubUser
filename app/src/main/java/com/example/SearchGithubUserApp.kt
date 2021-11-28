package com.example

import androidx.multidex.MultiDexApplication
import com.example.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger

class SearchGithubUserApp : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SearchGithubUserApp)
            modules(appModule)
            EmptyLogger()
        }
    }
}