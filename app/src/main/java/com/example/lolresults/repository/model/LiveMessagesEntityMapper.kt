package com.example.lolresults.repository.model

import com.example.lolresults.api.entityModel.*

class LiveMessagesEntityMapper {

    private fun GameStartedPayload.toModel() = GameStartedMessageModel()

    private fun KillPayload.toModel() = KillMessageModel(
        killer = killer.name,
        victim = victim.name,
        killerTeam = killer.teamId,
        victimTeam = victim.teamId
    )

    private fun BuildingDestroyedPayload.toModel() = BuildingDestroyedMessageModel(
        buildingType = buildingType,
        destroyerTeamId = destroyerTeamId
    )

    private fun EpicMonsterKillPayload.toModel() = EpicMonsterKillMessageModel(
        monsterType = monsterName,
        killerTeamId = killerTeamId,
        dragonType = dragonType
    )

    private fun GameEndedPayload.toModel() = GameEndedMessageModel(
        winnerId = winnerId
    )

    fun mapLiveMessage(payload: Payload): LiveMessageModel {
        return when(payload.getPayloadType()) {
            Payload.GAME_STARTED -> (payload as GameStartedPayload).toModel()

            Payload.BUILDING_DESTROYED -> (payload as BuildingDestroyedPayload).toModel()

            Payload.EPIC_MONSTER_KILL -> (payload as EpicMonsterKillPayload).toModel()

            Payload.KILL -> (payload as KillPayload).toModel()

            Payload.GAME_ENDED -> (payload as GameEndedPayload).toModel()

            else -> throw Exception("No puedo mappear $payload")
        }
    }
}