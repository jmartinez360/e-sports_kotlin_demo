package com.example.lolresults.datasource.livedata

import com.example.lolresults.api.entityModel.OccurrenceMessageEntity

interface LiveDataMessagesCallback {

    fun onOccurrenceReceived(occurrenceMessageEntity: OccurrenceMessageEntity)
}