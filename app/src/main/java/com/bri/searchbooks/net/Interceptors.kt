package com.bri.searchbooks.net

import okhttp3.Interceptor
import okhttp3.Response


class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val chainRequest = chain.request()
        val request = chainRequest.newBuilder().apply {
            // 카카오 키 헤더
            addHeader("Authorization", "KakaoAK a26fdc937e66259a7297f3a54b5db5ac")
        }.build()
        return chain.proceed(request)
    }
}