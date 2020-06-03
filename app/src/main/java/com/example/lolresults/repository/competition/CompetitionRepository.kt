package com.example.lolresults.repository.competition

import com.example.lolresults.api.entityModel.DummyEntity
import com.example.lolresults.repository.RepositoryCallback
import java.lang.Exception

interface CompetitionRepository {

    fun getDummyData(callback: RepositoryCallback<List<DummyEntity>, Exception>)

}