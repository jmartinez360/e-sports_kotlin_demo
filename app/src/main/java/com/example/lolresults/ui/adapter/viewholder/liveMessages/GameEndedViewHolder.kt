package com.example.lolresults.ui.adapter.viewholder.liveMessages

import android.view.View
import com.example.lolresults.R
import com.example.lolresults.repository.model.GameEndedMessageModel
import com.example.lolresults.repository.model.LiveMessageModel
import com.example.lolresults.repository.model.TeamModel
import com.example.lolresults.ui.adapter.viewholder.BaseViewHolder
import com.example.lolresults.utils.ImageUtils
import kotlinx.android.synthetic.main.game_finished_item.view.*

class GameEndedViewHolder constructor (itemView: View, teams: List<TeamModel>): BaseViewHolder<LiveMessageModel>(itemView, teams) {

    private lateinit var liveMessageModel: GameEndedMessageModel

    override fun bindItem(item: LiveMessageModel) {
        liveMessageModel = item as GameEndedMessageModel
        itemView.info.text = itemView.context.getString(R.string.team_has_won, getTeamNameById(liveMessageModel.winnerId))
        itemView.icon.setImageURI(ImageUtils.getImageUrl(liveMessageModel.winnerId))
    }
}