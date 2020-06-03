package com.example.lolresults.repository.fixture

import com.example.lolresults.repository.RepositoryCallback
import com.example.lolresults.repository.model.FixtureModel

interface FixtureRepository {

    fun getFixtures(from: Long, to: Long, callback: RepositoryCallback<List<FixtureModel>, Exception>)

    fun getFixturesByCompetition(competitionId: Int, callback: RepositoryCallback<List<FixtureModel>, Exception>)

    fun getFixturesById(fixtureId: Int, callback: RepositoryCallback<FixtureModel, Exception>)

}