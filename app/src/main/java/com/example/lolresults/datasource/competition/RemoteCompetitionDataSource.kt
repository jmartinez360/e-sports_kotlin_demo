package com.example.lolresults.datasource.competition

import com.example.lolresults.api.ApiResult
import com.example.lolresults.api.FixtureApiService
import com.example.lolresults.api.entityModel.DummyEntity
import com.example.lolresults.datasource.safeApiCall
import java.io.IOException

class RemoteCompetitionDataSource(private val fixtureApiService: FixtureApiService) :
    CompetitionDataSource {

    override suspend fun getDummyData(): ApiResult<DummyEntity>? {
        return safeApiCall(call = {
            val response = fixtureApiService.getDummyDataAsync().await()
            if (response.isSuccessful) {
                ApiResult.Success(response.body()!!)
            } else {
                ApiResult.Error(IOException(response.message()))
            }
        })
    }
}