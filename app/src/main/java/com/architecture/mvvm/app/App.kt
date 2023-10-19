package com.architecture.mvvm.app

import android.app.Application
import com.architecture.mvvm.di.apiModules
import com.architecture.mvvm.di.repositoryModules
import com.architecture.mvvm.di.useCaseModules
import com.architecture.mvvm.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(apiModules, repositoryModules, useCaseModules, viewModelModules)
        }
    }
}