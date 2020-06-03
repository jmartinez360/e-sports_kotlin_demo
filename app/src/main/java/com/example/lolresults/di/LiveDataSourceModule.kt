package com.example.lolresults.di

import com.example.lolresults.api.FixtureWebSocketService
import com.example.lolresults.datasource.livedata.FixtureLiveMessagesDataSource
import com.example.lolresults.datasource.livedata.RemoteFixtureLiveMessagesDataSource
import org.koin.dsl.module

val liveDataSourceModule = module {
    single { provideRemoteFixtureLiveMessageDataSource(get()) }
}


fun provideRemoteFixtureLiveMessageDataSource(fixtureWebSocketService: FixtureWebSocketService): FixtureLiveMessagesDataSource {
    return RemoteFixtureLiveMessagesDataSource(fixtureWebSocketService)
}