package com.example.lolresults.datasource.competition

import com.example.lolresults.api.ApiResult
import com.example.lolresults.api.entityModel.DummyEntity

interface CompetitionDataSource {

    suspend fun getDummyData(): ApiResult<DummyEntity>?
}