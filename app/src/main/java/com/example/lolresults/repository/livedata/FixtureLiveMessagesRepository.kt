package com.example.lolresults.repository.livedata

import com.example.lolresults.repository.RepositoryCallback
import com.example.lolresults.repository.model.LiveMessageModel

interface FixtureLiveMessagesRepository {

    fun subscribeToFixtureOccurrences(idFixture: Int, callback: RepositoryCallback<LiveMessageModel, Nothing>)
}