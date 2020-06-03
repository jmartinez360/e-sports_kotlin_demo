package com.example.lolresults.di

import android.app.Application
import com.example.lolresults.BuildConfig
import com.example.lolresults.api.FixtureApiService
import com.example.lolresults.utils.HeadersInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    single { provideHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideFixtureApiService(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideHttpClient(app: Application): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(HeadersInterceptor(app)).build()
}

fun provideFixtureApiService(retrofit: Retrofit): FixtureApiService =
    retrofit.create(FixtureApiService::class.java)

