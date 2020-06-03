package com.example.lolresults.api.entityModel

import com.google.gson.annotations.SerializedName

data class DummyEntity(@SerializedName("status") val name: String)

data class DummyResponse(val type: String, val time: String)

data class DummySubscribe(val type: String = "subscribe", val channels: List<DummyChannel> = listOf(
    DummyChannel("heartbeat", listOf("ETH-EUR"))
))

data class DummyChannel(val name: String, @SerializedName("product_ids") val ids: List<String>)



data class CompetitionEntity(val id: Int, val name: String)

data class FixtureEntity(val id: Int, val competition: CompetitionEntity, @SerializedName("scheduledStartTime") val startTime: Long,
                         val format: FixtureFormatEntity, @SerializedName("participants") val teams: List<TeamEntity>,
                         val links: List<LinkEntity>?, val winnerId: Int?)

data class FixtureFormatEntity(val name: String, val value: Int)

data class LinkEntity(val link: String)

data class TeamEntity(val id: Int, val name: String, val score: Int)

data class FixtureEntities(val fixtures: List<FixtureEntity>)

data class FixtureMetadataEntity(@SerializedName("value") val streamUrl: String? = null)