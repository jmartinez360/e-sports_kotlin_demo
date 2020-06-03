package com.example.lolresults.ui.adapter.viewholder.liveMessages

import android.view.View
import com.example.lolresults.R
import com.example.lolresults.repository.model.BuildingDestroyedMessageModel
import com.example.lolresults.repository.model.LiveMessageModel
import com.example.lolresults.repository.model.TeamModel
import com.example.lolresults.ui.adapter.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.building_destroyed_item.view.*

class BuildingDestroyedViewHolder constructor (itemView: View, teams: List<TeamModel>): BaseViewHolder<LiveMessageModel>(itemView, teams) {

    private lateinit var messageModel: BuildingDestroyedMessageModel

    override fun bindItem(item: LiveMessageModel) {
        messageModel = item as BuildingDestroyedMessageModel
        setupIcon()
        setupInfo()
    }

    private fun setupIcon() {
        when(messageModel.buildingType) {
            BuildingDestroyedMessageModel.TURRET_TYPE ->
                itemView.icon.setActualImageResource(R.drawable.turret_icon)

            BuildingDestroyedMessageModel.INHIBITOR_TYPE ->
                itemView.icon.setActualImageResource(R.drawable.red_inhibitor)
        }
    }

    private fun setupInfo() {
        itemView.info.text = getBuildingDestroyedText()
    }

    private fun getBuildingDestroyedText(): String {
        return when(messageModel.buildingType) {
            BuildingDestroyedMessageModel.TURRET_TYPE ->
                itemView.context.getString(R.string.turret_destroyed, getTeamNameById(messageModel.destroyerTeamId))

            else ->
                itemView.context.getString(R.string.inhibitor_destroyed, getTeamNameById(messageModel.destroyerTeamId))
        }
    }
}