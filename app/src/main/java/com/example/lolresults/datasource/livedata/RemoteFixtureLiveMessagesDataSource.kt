package com.example.lolresults.datasource.livedata

import android.util.Log
import com.example.lolresults.BuildConfig
import com.example.lolresults.api.FixtureWebSocketService
import com.example.lolresults.api.entityModel.AuthMessageEntity
import com.example.lolresults.api.entityModel.AuthToken
import com.example.lolresults.api.entityModel.OccurrenceMessageEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.consumeEach

@ExperimentalCoroutinesApi
class RemoteFixtureLiveMessagesDataSource(private val fixtureWebSocketService: FixtureWebSocketService)
    : FixtureLiveMessagesDataSource {

    override suspend fun receiveOccurrenceMessages(onResponse: (message: OccurrenceMessageEntity) -> Unit) {
        fixtureWebSocketService.receiveLiveMessages().consumeEach {
            when (it) {
                is AuthMessageEntity -> sendAuthToken()
                is OccurrenceMessageEntity -> onResponse(it)
            }
        }
    }

    private fun sendAuthToken() {
        fixtureWebSocketService.sendAuthToken(AuthToken(BuildConfig.API_TOKEN))
    }
}