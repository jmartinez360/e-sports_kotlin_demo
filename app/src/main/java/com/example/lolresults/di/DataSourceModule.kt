package com.example.lolresults.di

import com.example.lolresults.api.FixtureApiService
import com.example.lolresults.datasource.competition.CompetitionDataSource
import com.example.lolresults.datasource.competition.RemoteCompetitionDataSource
import com.example.lolresults.datasource.fixture.FixtureDataSource
import com.example.lolresults.datasource.fixture.RemoteFixtureDataSource
import org.koin.dsl.module


val dataSourceModule = module {
    single { provideRemoteCompetitionDataSource(get()) }
    single { provideRemoteFixtureDataSource(get()) }
}

fun provideRemoteCompetitionDataSource(fixtureApiService: FixtureApiService): CompetitionDataSource {
    return RemoteCompetitionDataSource(fixtureApiService)
}

fun provideRemoteFixtureDataSource(fixtureApiService: FixtureApiService): FixtureDataSource {
    return RemoteFixtureDataSource(fixtureApiService)
}
