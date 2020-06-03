package com.example.lolresults.ui.adapter.viewholder.liveMessages

import android.view.View
import com.example.lolresults.repository.model.LiveMessageModel
import com.example.lolresults.repository.model.TeamModel
import com.example.lolresults.ui.adapter.viewholder.BaseViewHolder
import com.example.lolresults.utils.ImageUtils
import kotlinx.android.synthetic.main.game_started_item.view.*

class GameStartedViewHolder constructor (itemView: View, teams: List<TeamModel>): BaseViewHolder<LiveMessageModel>(itemView, teams) {

    override fun bindItem(item: LiveMessageModel) {
        itemView.leftIcon.setImageURI(ImageUtils.getImageUrl(teams!![0].id))
        itemView.rightIcon.setImageURI(ImageUtils.getImageUrl(teams[1].id))
    }

}