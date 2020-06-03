package com.example.lolresults.repository.model

data class FixtureModel(
    val id: Int, val startTime: Long,
    val format: FixtureFormatModel, val teams: List<TeamModel>,
    val liveEventsLink: String?, val winnerId: Int?, val streamUrl: String?, val competition: CompetitionModel
)

data class FixtureFormatModel(val name: String, val value: Int)

data class TeamModel(val id: Int, val name: String? = "", val score: Int)

data class CompetitionModel(val id: Int, val name: String)

