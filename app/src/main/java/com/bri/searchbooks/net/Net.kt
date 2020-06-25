package com.bri.searchbooks.net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Net(
    private val connectTimeout: Long = 60L,
    private val writeTimeout: Long = 60L,
    private val readTimeout: Long = 60L
) {
    val retrofit: Retrofit

    init {
        val httpClient = getClientBuilder().apply {
            addInterceptor(HeaderInterceptor())
            connectTimeout(connectTimeout, TimeUnit.SECONDS)
            writeTimeout(writeTimeout, TimeUnit.SECONDS)
            readTimeout(readTimeout, TimeUnit.SECONDS)

        }.build()

        retrofit = Retrofit.Builder().apply {
            baseUrl(baseUrl) // BaseUrl 설정
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            client(httpClient)
        }.build()
    }


    private fun getClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient().newBuilder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
    }

    companion object {
        const val baseUrl = "https://dapi.kakao.com/"
    }
}