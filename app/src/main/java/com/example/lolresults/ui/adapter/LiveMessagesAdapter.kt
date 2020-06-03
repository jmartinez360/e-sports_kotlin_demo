package com.example.lolresults.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lolresults.R
import com.example.lolresults.repository.model.LiveMessageModel
import com.example.lolresults.repository.model.TeamModel
import com.example.lolresults.ui.adapter.viewholder.BaseViewHolder
import com.example.lolresults.ui.adapter.viewholder.liveMessages.*

class LiveMessagesAdapter constructor(private val teams: List<TeamModel>): RecyclerView.Adapter<BaseViewHolder<LiveMessageModel>>() {

    private val messagesItems = mutableListOf<LiveMessageModel>()

    fun addMessage(message: LiveMessageModel) {
        messagesItems.add(message)
        notifyItemInserted(messagesItems.size - 1)
    }

    override fun getItemViewType(position: Int) = messagesItems[position].getType()

    override fun getItemCount() = messagesItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<LiveMessageModel> {
        return when(viewType) {
            LiveMessageModel.BUILDING_DESTROYED -> BuildingDestroyedViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.building_destroyed_item, parent, false), teams)

            LiveMessageModel.EPIC_MONSTER_KILL -> EpicMonsterKillViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.epic_monster_item, parent, false), teams)

            LiveMessageModel.GAME_ENDED -> GameEndedViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.game_finished_item, parent, false), teams)

            LiveMessageModel.KILL -> KillViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.kill_item, parent, false))

            LiveMessageModel.GAME_STARTED -> GameStartedViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.game_started_item, parent, false), teams)

            else -> throw Exception()

        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<LiveMessageModel>, position: Int) {
        holder.bindItem(messagesItems[position])
    }
}