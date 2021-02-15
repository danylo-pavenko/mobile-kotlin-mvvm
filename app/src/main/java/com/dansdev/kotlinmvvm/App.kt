package com.dansdev.kotlinmvvm

import android.app.Application
import com.dansdev.kotlinmvvm.di.appModule
import com.dansdev.kotlinmvvm.di.appViewModelModule
import com.dansdev.kotlinmvvm.di.dataBaseModule
import com.dansdev.kotlinmvvm.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.KoinExperimentalAPI
import org.koin.core.context.startKoin
import timber.log.Timber

@KoinExperimentalAPI
class App: Application() {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        setupLibs()
        setupDI()
    }

    private fun setupLibs() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setupDI() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            fragmentFactory()
            modules(appModule, dataBaseModule, useCaseModule, appViewModelModule)
        }
    }
}
