package com.example.lolresults.api.entityModel

open class LiveMessage(val type: String) {

    companion object {
        const val AUTH_MESSAGE = "auth"
        const val OCCURRENCE_MESSAGE = "occurrence"
    }
}

abstract class Payload(val name: String) {
    var fixtureId: Int? = null
    var gameNumber: Int? = null

    abstract fun getPayloadType(): String

    companion object {
        const val GAME_STARTED = "game_started"
        const val KILL = "kill"
        const val BUILDING_DESTROYED = "building_destroyed"
        const val EPIC_MONSTER_KILL = "epic_monster_kill"
        const val GAME_ENDED = "game_ended"
    }
}

class AuthMessageEntity: LiveMessage(AUTH_MESSAGE)

data class OccurrenceMessageEntity(val payload: Payload): LiveMessage(OCCURRENCE_MESSAGE)

data class GameStartedPayload(val participants: List<ParticipantEntity>): Payload(GAME_STARTED) {
    override fun getPayloadType() = GAME_STARTED
}

data class KillPayload(val killer: KillerVictimEntity, val victim: KillerVictimEntity): Payload(KILL) {
    override fun getPayloadType() = KILL
}

data class BuildingDestroyedPayload(val buildingType: String, val destroyerTeamId: Int): Payload(BUILDING_DESTROYED) {
    override fun getPayloadType() = BUILDING_DESTROYED
}

data class EpicMonsterKillPayload(val monsterName: String, val killerTeamId: Int, val dragonType: String? = null)
    : Payload(EPIC_MONSTER_KILL) {
    override fun getPayloadType() = EPIC_MONSTER_KILL

}

data class GameEndedPayload(val winnerId: Int): Payload(GAME_ENDED) {
    override fun getPayloadType() = GAME_ENDED
}

data class ParticipantEntity(val id: Int, val color: String)

data class KillerVictimEntity(val id: Int, val name: String, val teamId: Int)

data class AuthToken(val token: String)








