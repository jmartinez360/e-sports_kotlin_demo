package com.example.lolresults.repository.competition

import com.example.lolresults.api.ApiResult
import com.example.lolresults.api.entityModel.DummyEntity
import com.example.lolresults.datasource.competition.CompetitionDataSource
import com.example.lolresults.repository.RepositoryCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RemoteCompetitionRepository(private val competitionDataSource: CompetitionDataSource):
    CompetitionRepository {

    private val scope = CoroutineScope(Dispatchers.IO)

    override fun getDummyData(callback: RepositoryCallback<List<DummyEntity>, Exception>) {
        scope.launch {
            val result = competitionDataSource.getDummyData()
            result?.let {
                when (it) {
                    is ApiResult.Success -> {
                        callback.onSuccess(listOf(it.data))
                    }
                    is ApiResult.Error -> callback.onError(it.exception)
                }
            }
        }
    }
}