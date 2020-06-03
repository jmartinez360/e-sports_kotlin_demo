package com.example.lolresults.datasource.fixture

import com.example.lolresults.api.ApiResult
import com.example.lolresults.api.FixtureApiService
import com.example.lolresults.api.entityModel.FixtureEntities
import com.example.lolresults.api.entityModel.FixtureEntity
import com.example.lolresults.api.entityModel.FixtureMetadataEntity
import com.example.lolresults.datasource.safeApiCall
import retrofit2.Response
import java.io.IOException

class RemoteFixtureDataSource(private val fixtureApiService: FixtureApiService): FixtureDataSource {

    override suspend fun getFixturesByCompetition(id: Int): ApiResult<FixtureEntities>? {
        return safeApiCall(call = {
            val response = fixtureApiService.getFixtureByCompetitionAsync(id).await()
            handleApiResponse(response)
        })
    }

    override suspend fun getFixtures(from: Long, to: Long): ApiResult<FixtureEntities>? {
        return safeApiCall(call = {
            val response = fixtureApiService.getFixturesAsync(from, to).await()
            handleApiResponse(response)
        })
    }

    override suspend fun getFixtureById(fixtureId: Int): ApiResult<FixtureEntity>? {
        return safeApiCall(call = {
            val response = fixtureApiService.getFixtureAsync(fixtureId).await()
            handleApiResponse(response)
        })
    }

    override suspend fun getFixtureMetadata(fixtureId: Int): ApiResult<FixtureMetadataEntity>? {
        return safeApiCall(call = {
            val response = fixtureApiService.getFixtureMetadataAsync(fixtureId, STREAM_METADATA).await()
            handleApiResponse(response)
        })
    }

    private fun <T : Any> handleApiResponse(response: Response<T>): ApiResult<T> {
        return if (response.isSuccessful) {
            ApiResult.Success(response.body()!!)
        } else {
            ApiResult.Error(IOException(response.message()))
        }
    }

    companion object {
        private const val STREAM_METADATA = "streamUrl"
    }
}