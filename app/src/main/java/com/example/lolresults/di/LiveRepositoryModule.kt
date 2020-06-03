package com.example.lolresults.di

import com.example.lolresults.datasource.livedata.FixtureLiveMessagesDataSource
import com.example.lolresults.repository.livedata.FixtureLiveMessagesRepository
import com.example.lolresults.repository.livedata.RemoteFixtureLiveMessagesRepository
import com.example.lolresults.repository.model.LiveMessagesEntityMapper
import org.koin.dsl.module

val liveRepositoryModule = module {
    single { provideRemoteFixtureLiveMessagesRepository(get()) }
}


fun provideRemoteFixtureLiveMessagesRepository(liveMessagesDataSource: FixtureLiveMessagesDataSource): FixtureLiveMessagesRepository {
    return RemoteFixtureLiveMessagesRepository(liveMessagesDataSource, LiveMessagesEntityMapper())
}