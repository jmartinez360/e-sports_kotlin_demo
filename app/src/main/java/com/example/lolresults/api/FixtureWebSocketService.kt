package com.example.lolresults.api

import com.example.lolresults.api.entityModel.AuthToken
import com.example.lolresults.api.entityModel.LiveMessage
import com.tinder.scarlet.websocket.WebSocketEvent
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow

interface FixtureWebSocketService {

    @Receive
    fun receiveLiveMessages(): ReceiveChannel<LiveMessage>

    @Receive
    fun observeEvents(): ReceiveChannel<WebSocketEvent>

    @Send
    fun sendAuthToken(authToken: AuthToken)

}