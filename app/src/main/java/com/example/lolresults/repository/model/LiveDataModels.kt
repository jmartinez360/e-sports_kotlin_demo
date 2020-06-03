package com.example.lolresults.repository.model

interface LiveMessageModel {
    fun getType(): Int

    companion object {
        const val GAME_STARTED = 1
        const val KILL = 2
        const val BUILDING_DESTROYED = 3
        const val EPIC_MONSTER_KILL = 4
        const val GAME_ENDED = 5
    }
}

class GameStartedMessageModel: LiveMessageModel {
    override fun getType() = LiveMessageModel.GAME_STARTED
}

data class KillMessageModel(val killer: String, val killerTeam: Int, val victim: String, val victimTeam: Int): LiveMessageModel {
    override fun getType() = LiveMessageModel.KILL
}

data class BuildingDestroyedMessageModel(val buildingType: String, val destroyerTeamId: Int) : LiveMessageModel {
    override fun getType() = LiveMessageModel.BUILDING_DESTROYED

    companion object {
        const val TURRET_TYPE = "turret"
        const val INHIBITOR_TYPE = "inhibitor"
    }
}

data class EpicMonsterKillMessageModel(val monsterType: String, val killerTeamId: Int, val dragonType: String? = null) : LiveMessageModel {
    override fun getType() = LiveMessageModel.EPIC_MONSTER_KILL

    companion object {
        const val DRAGON_TYPE = "dragon"
        const val RIFT_HERALD_TYPE = "riftHerald"
        const val BARON_TYPE = "baron"

        const val AIR_DRAGON_TYPE = "air"
        const val EARTH_DRAGON_TYPE = "earth"
        const val WATER_DRAGON_TYPE = "water"
        const val FIRE_DRAGON_TYPE = "fire"
        const val ELDER_DRAGON_TYPE = "elder"
    }
}

data class GameEndedMessageModel(val winnerId: Int): LiveMessageModel {
    override fun getType() = LiveMessageModel.GAME_ENDED
}

