package com.example.lolresults.repository.fixture

import com.example.lolresults.api.entityModel.FixtureEntity
import com.example.lolresults.api.entityModel.FixtureMetadataEntity
import com.example.lolresults.datasource.fixture.FixtureDataSource
import com.example.lolresults.repository.Repository
import com.example.lolresults.repository.RepositoryCallback
import com.example.lolresults.repository.model.EntityMapper
import com.example.lolresults.repository.model.FixtureModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RemoteFixtureRepository(private val fixtureDataSource: FixtureDataSource,
                              private val modelMapper: EntityMapper): Repository(), FixtureRepository {

    private val scope = CoroutineScope(Dispatchers.IO)

    override fun getFixtures(from: Long, to: Long, callback: RepositoryCallback<List<FixtureModel>, Exception>) {
        scope.launch {
            val result = fixtureDataSource.getFixtures(from, to)
            handleApiResult(result,
                onSuccessFunction = { callback.onSuccess(modelMapper.mapFixtureEntityList(it.fixtures)) },
                onErrorFunction = { callback.onError(it) })
        }
    }

    override fun getFixturesByCompetition(competitionId: Int, callback: RepositoryCallback<List<FixtureModel>, Exception>) {
        scope.launch {
            val result = fixtureDataSource.getFixturesByCompetition(competitionId)
            handleApiResult(result,
                onSuccessFunction =  { callback.onSuccess(modelMapper.mapFixtureEntityList(it.fixtures)) },
                onErrorFunction = { callback.onError(it) })
        }
    }

    override fun getFixturesById(fixtureId: Int, callback: RepositoryCallback<FixtureModel, Exception>) {
        scope.launch {
            try {
                getFixtureAndMetadataThenCallSuccessCallback(fixtureId, callback)
            } catch (exception: Exception) {
                callback.onError(exception)
            }
        }
    }

    private suspend fun getFixtureAndMetadataThenCallSuccessCallback(fixtureId: Int, callback: RepositoryCallback<FixtureModel, Exception>) {
        val fixture = scope.async {  getFixture(fixtureId)  }
        val fixtureStreamLink = scope.async {  getFixtureMetadata(fixtureId) }
        callback.onSuccess( modelMapper.mapFixtureEntity(fixture.await(), fixtureStreamLink.await().streamUrl) )
    }

    private suspend fun getFixture(fixtureId: Int): FixtureEntity {
        val result = fixtureDataSource.getFixtureById(fixtureId)
        return handleApiResultAndReturnData(result)
    }

    private suspend fun getFixtureMetadata(fixtureId: Int): FixtureMetadataEntity {
        return try {
            val result = fixtureDataSource.getFixtureMetadata(fixtureId)
            handleApiResultAndReturnData(result)
        } catch (exception: Exception) {
            FixtureMetadataEntity()
        }
    }

}