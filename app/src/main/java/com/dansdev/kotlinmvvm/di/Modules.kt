package com.dansdev.kotlinmvvm.di

import com.dansdev.kotlinmvvm.data.PrefsRepository
import com.dansdev.kotlinmvvm.data.impl.PrefsRepositoryImpl
import com.dansdev.kotlinmvvm.domain.AnalyticsManager
import com.dansdev.kotlinmvvm.domain.analytic.ConsoleLogger
import com.dansdev.kotlinmvvm.domain.analytic.EventLogger
import com.dansdev.kotlinmvvm.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    //Managers, Utils
    single<PrefsRepository> { PrefsRepositoryImpl(get()) }
    single<EventLogger> { ConsoleLogger() }
    single { AnalyticsManager(get()) }
}

val dataBaseModule = module {
    // Database, Dao, Mappers
}

val useCaseModule = module {
    // UseCases
}

val appViewModelModule = module {
    // ViewModels
    viewModel { MainViewModel(get()) }
//    viewModel { LoginViewModel(get(), get(), get(), get()) }
}
