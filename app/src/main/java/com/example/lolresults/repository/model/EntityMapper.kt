package com.example.lolresults.repository.model

import com.example.lolresults.api.entityModel.*

class EntityMapper {

    private fun TeamEntity.toModel() = TeamModel(
        id = id,
        name = name,
        score = score
    )

    private fun CompetitionEntity.toModel() = CompetitionModel(
        id = id,
        name = name
    )

    private fun FixtureFormatEntity.toModel() = FixtureFormatModel(
        name = name,
        value = value
    )

    private fun FixtureEntity.toModel(streamUrl: String?) = FixtureModel(
        id = id,
        startTime = startTime,
        format = format.toModel(),
        teams = mapEntityList(teams) { it.toModel() },
        liveEventsLink = getLiveEventsLink(links),
        winnerId = winnerId,
        streamUrl = streamUrl,
        competition = competition.toModel()
    )

    fun mapFixtureEntity(fixture: FixtureEntity, streamUrl: String?): FixtureModel {
        return fixture.toModel(streamUrl)
    }

    private fun getLiveEventsLink(linkList: List<LinkEntity>?): String? {
        var result: String? = null
        if (!linkList.isNullOrEmpty()) {
            result = linkList[0].link
        }
        return result
    }

    fun mapFixtureEntityList(list: List<FixtureEntity>?, streamLink: String? = null): List<FixtureModel> {
        val orderedList = list?.sortedBy{ it.startTime } ?: emptyList()
        return mapEntityList(orderedList) {it.toModel(streamLink)}
    }

    private fun <T: Any, A> mapEntityList(entityList: List<A>?, mapFunction: (item: A) -> T): List<T> {
        val result = mutableListOf<T>()
        entityList?.let { list -> list.forEach { result.add(mapFunction.invoke(it)) }}
        return result
    }
}