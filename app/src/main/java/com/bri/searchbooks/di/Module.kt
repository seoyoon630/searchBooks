package com.bri.searchbooks.di

import com.bri.searchbooks.common.createNetService
import com.bri.searchbooks.view.main.repo.MainDataSource
import com.bri.searchbooks.view.main.repo.MainRepository
import com.bri.searchbooks.view.main.repo.MainRepositoryImpl
import com.bri.searchbooks.view.MainViewModel
import com.bri.searchbooks.net.Net
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * DI : koin module
 */
val netModule = module {
    single { Net() }
}

val mainModule = module {
    single { createNetService<MainDataSource>(get()) }
    single<MainRepository> { MainRepositoryImpl(get()) }
    viewModel { MainViewModel(get()) }
}

val module = listOf(netModule, mainModule)
