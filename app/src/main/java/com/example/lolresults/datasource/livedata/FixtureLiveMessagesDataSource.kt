package com.example.lolresults.datasource.livedata

import com.example.lolresults.api.entityModel.OccurrenceMessageEntity

interface FixtureLiveMessagesDataSource {

    suspend fun receiveOccurrenceMessages(onResponse: (message: OccurrenceMessageEntity) -> Unit)

}