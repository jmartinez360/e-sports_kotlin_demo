package com.example.lolresults.api

import com.example.lolresults.api.entityModel.DummyEntity
import com.example.lolresults.api.entityModel.FixtureEntities
import com.example.lolresults.api.entityModel.FixtureEntity
import com.example.lolresults.api.entityModel.FixtureMetadataEntity
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FixtureApiService {

    @GET("competitions/{competitionId}/fixtures")
    fun getDummyDataAsync(): Deferred<Response<DummyEntity>>

    @GET("competitions/{competitionId}/fixtures")
    fun getFixtureByCompetitionAsync(@Path("competitionId") competitionId: Int): Deferred<Response<FixtureEntities>>

    @GET("fixtures/{id}")
    fun getFixtureAsync(@Path("id") fixtureId: Int): Deferred<Response<FixtureEntity>>

    @GET("fixtures/{id}/metadata/{attribute}")
    fun getFixtureMetadataAsync(@Path("id") fixtureId: Int, @Path("attribute") metadataAttribute: String) :
            Deferred<Response<FixtureMetadataEntity>>

    @GET("fixtures")
    fun getFixturesAsync(@Query("from") fromTimestamp: Long, @Query("to") toTimestamp: Long,
                    @Query("sport") sport: String? = "lol") : Deferred<Response<FixtureEntities>>
}