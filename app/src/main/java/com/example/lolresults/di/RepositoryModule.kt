package com.example.lolresults.di

import com.example.lolresults.datasource.competition.CompetitionDataSource
import com.example.lolresults.datasource.fixture.FixtureDataSource
import com.example.lolresults.repository.competition.CompetitionRepository
import com.example.lolresults.repository.competition.RemoteCompetitionRepository
import com.example.lolresults.repository.fixture.FixtureRepository
import com.example.lolresults.repository.fixture.RemoteFixtureRepository
import com.example.lolresults.repository.model.EntityMapper
import org.koin.dsl.module


val repositoryModule = module {
    single { provideRemoteCompetitionRepository(get()) }
    single { provideRemoteFixtureRepository(get()) }
}

fun provideRemoteCompetitionRepository(competitionDataSource: CompetitionDataSource): CompetitionRepository {
    return RemoteCompetitionRepository(competitionDataSource)
}

fun provideRemoteFixtureRepository(remoteFixtureDataSource: FixtureDataSource): FixtureRepository {
    return RemoteFixtureRepository(remoteFixtureDataSource, EntityMapper())
}

