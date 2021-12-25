package com.mwaiterdev.domain.api

import com.mwaiterdev.domain.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class WaiterApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader(APPLICATION_ID, BuildConfig.APP_ID)
                .addHeader(APPLICATION_TOKEN, BuildConfig.API_TOKEN)
                .build()
        )

    companion object {
        const val APPLICATION_ID = "AppId"
        const val APPLICATION_TOKEN = "Token"
    }
}