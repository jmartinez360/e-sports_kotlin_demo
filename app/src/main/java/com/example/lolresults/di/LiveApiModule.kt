package com.example.lolresults.di

import com.example.lolresults.api.FixtureWebSocketService
import com.example.lolresults.api.entityModel.*
import com.example.lolresults.utils.RuntimeTypeAdapterFactory
import com.google.gson.GsonBuilder
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.messageadapter.gson.GsonMessageAdapter
import com.tinder.scarlet.websocket.ShutdownReason
import com.tinder.scarlet.websocket.okhttp.OkHttpWebSocket
import com.tinder.streamadapter.coroutines.CoroutinesStreamAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.dsl.module


fun getLiveApiModule(socketUrl: String) = module {
    single { provideGsonFactory() }
    single { provideFixtureWebSocketService(socketUrl, get(), get()) }
}

//val liveApiModule =

fun provideFixtureWebSocketService(socketUrl: String, client: OkHttpClient, factory: GsonMessageAdapter.Factory): FixtureWebSocketService {
    val scarlet = Scarlet(
        OkHttpWebSocket(
            client,
            OkHttpWebSocket.SimpleRequestFactory(
                //{ Request.Builder().url("wss://api.gamescorekeeper.com/v1/liveapi/59237").build() },
                { Request.Builder().url(socketUrl).build() },
                { ShutdownReason.GRACEFUL }
            )),
        Scarlet.Configuration(messageAdapterFactories = listOf(factory),
            streamAdapterFactories = listOf(CoroutinesStreamAdapterFactory())))
    return scarlet.create()
}

fun provideGsonFactory(): GsonMessageAdapter.Factory {
    val messageTypeFactory = RuntimeTypeAdapterFactory.of(LiveMessage::class.java, "type")
        .registerSubtype(AuthMessageEntity::class.java, LiveMessage.AUTH_MESSAGE)
        .registerSubtype(OccurrenceMessageEntity::class.java, LiveMessage.OCCURRENCE_MESSAGE)

    val occurrenceTypeFactory = RuntimeTypeAdapterFactory.of(Payload::class.java, "name")
        .registerSubtype(GameStartedPayload::class.java, Payload.GAME_STARTED)
        .registerSubtype(GameEndedPayload::class.java, Payload.GAME_ENDED)
        .registerSubtype(EpicMonsterKillPayload::class.java, Payload.EPIC_MONSTER_KILL)
        .registerSubtype(KillPayload::class.java, Payload.KILL)
        .registerSubtype(BuildingDestroyedPayload::class.java, Payload.BUILDING_DESTROYED)


    return GsonMessageAdapter.Factory(
        GsonBuilder()
        .registerTypeAdapterFactory(messageTypeFactory)
        .registerTypeAdapterFactory(occurrenceTypeFactory)
        .create())
}