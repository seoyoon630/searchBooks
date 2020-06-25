package com.bri.searchbooks.di

import com.bri.searchbooks.ext.createNetService
import com.bri.searchbooks.view.main.MainDataSource
import com.bri.searchbooks.view.main.MainRepository
import com.bri.searchbooks.view.main.MainRepositoryImpl
import com.bri.searchbooks.view.main.MainViewModel
import com.bri.searchbooks.net.Net
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val netModule = module {
    single { Net() }
}

val mainModule = module {
    single { createNetService<MainDataSource>(get()) }
    single<MainRepository> { MainRepositoryImpl(get()) }
    viewModel { MainViewModel(get()) }
}

val module = listOf(netModule, mainModule)
