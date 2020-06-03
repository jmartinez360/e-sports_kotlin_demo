package com.example.lolresults.ui.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.lolresults.repository.model.TeamModel

abstract class BaseViewHolder <T> constructor (itemView: View, open val teams: List<TeamModel>? = emptyList()): RecyclerView.ViewHolder(itemView) {

    abstract fun bindItem(item: T)

    fun getTeamNameById(teamId: Int): String {
        return teams!!.findLast { it.id == teamId }?.name ?: ""
    }
}