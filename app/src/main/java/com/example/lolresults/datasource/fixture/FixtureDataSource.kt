package com.example.lolresults.datasource.fixture

import com.example.lolresults.api.ApiResult
import com.example.lolresults.api.entityModel.DummyResponse
import com.example.lolresults.api.entityModel.FixtureEntities
import com.example.lolresults.api.entityModel.FixtureEntity
import com.example.lolresults.api.entityModel.FixtureMetadataEntity
import com.example.lolresults.datasource.livedata.SocketConnectionCallback
import kotlinx.coroutines.channels.ReceiveChannel

interface FixtureDataSource {

    suspend fun getFixturesByCompetition(id: Int): ApiResult<FixtureEntities>?

    suspend fun getFixtures(from: Long, to: Long): ApiResult<FixtureEntities>?

    suspend fun getFixtureById(fixtureId: Int): ApiResult<FixtureEntity>?

    suspend fun getFixtureMetadata(fixtureId: Int): ApiResult<FixtureMetadataEntity>?

}