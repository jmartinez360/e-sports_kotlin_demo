package com.example.lolresults.repository.livedata

import com.example.lolresults.api.entityModel.OccurrenceMessageEntity
import com.example.lolresults.datasource.livedata.FixtureLiveMessagesDataSource
import com.example.lolresults.repository.RepositoryCallback
import com.example.lolresults.repository.model.LiveMessageModel
import com.example.lolresults.repository.model.LiveMessagesEntityMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RemoteFixtureLiveMessagesRepository(private val liveDataSource: FixtureLiveMessagesDataSource, private val mapper: LiveMessagesEntityMapper)
    : FixtureLiveMessagesRepository {

    private val scope = CoroutineScope(Dispatchers.IO)

    override fun subscribeToFixtureOccurrences(idFixture: Int, callback: RepositoryCallback<LiveMessageModel, Nothing>) {
        scope.launch {
            liveDataSource.receiveOccurrenceMessages { callback.onSuccess(getLiveMessage(it)) }
        }
    }

    private fun getLiveMessage(occurrence: OccurrenceMessageEntity): LiveMessageModel {
        return mapper.mapLiveMessage(occurrence.payload)
    }
}