package com.bri.searchbooks

import android.app.Application
import com.bri.searchbooks.di.module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

lateinit var kakaoKey : String
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // koin
        startKoin {
            androidContext(this@MainApplication)
            modules(module)
        }

        kakaoKey = resources.getString(R.string.kakao_key)
    }
}