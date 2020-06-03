package com.example.lolresults.ui.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.lolresults.R
import com.example.lolresults.repository.model.FixtureModel
import com.example.lolresults.repository.model.TeamModel
import com.example.lolresults.ui.adapter.ItemClickListener
import com.example.lolresults.utils.ImageUtils
import com.example.lolresults.utils.DateUtils
import kotlinx.android.synthetic.main.fixture_item.view.*

class FixtureViewHolder constructor(itemView: View, private val itemClickListener: ItemClickListener<FixtureModel>) :
    RecyclerView.ViewHolder(itemView) {

    private lateinit var fixture: FixtureModel
    private lateinit var leftTeam: TeamModel
    private lateinit var rightTeam: TeamModel

    fun bindItem(item: FixtureModel) {
        fixture = item
        setupCompetition()
        setupTeams()
        setupMatchDate()
        setupItemClick()
    }

    private fun setupCompetition() {
        itemView.competition.text = fixture.competition.name
    }

    private fun setupTeams() {
        leftTeam = fixture.teams[LEFT_TEAM_INDEX]
        rightTeam = fixture.teams[RIGHT_TEAM_INDEX]
        setupTeamNames()
        setupResult()
        setupShields()
    }

    private fun setupTeamNames() {
        itemView.leftTeamName.text = leftTeam.name
        itemView.rightTeamName.text = rightTeam.name
    }

    private fun setupResult() {
        itemView.result.text = itemView.context.getString(
            R.string.fixture_score, leftTeam.score, rightTeam.score)
        setupFixtureFormat()
    }

    private fun setupFixtureFormat() {
        itemView.matchFormat.text = itemView.context.getString(R.string.fixture_format, fixture.format.value)
    }

    private fun setupShields() {
        itemView.leftTeamShield.setImageURI(ImageUtils.getImageUrl(leftTeam.id))
        itemView.rightTeamShield.setImageURI(ImageUtils.getImageUrl(rightTeam.id))
    }

    private fun setupMatchDate() {
        itemView.matchDate.text = DateUtils.getDateFormatted(fixture.startTime)
    }

    private fun setupItemClick() {
        itemView.setOnClickListener { itemClickListener.onItemClick(fixture) }
    }

    companion object {
        const val LEFT_TEAM_INDEX = 0
        const val RIGHT_TEAM_INDEX = 1
    }
}