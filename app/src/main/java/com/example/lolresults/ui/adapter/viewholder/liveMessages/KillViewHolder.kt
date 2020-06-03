package com.example.lolresults.ui.adapter.viewholder.liveMessages

import android.view.View
import com.example.lolresults.R
import com.example.lolresults.repository.model.KillMessageModel
import com.example.lolresults.repository.model.LiveMessageModel
import com.example.lolresults.ui.adapter.viewholder.BaseViewHolder
import com.example.lolresults.utils.ImageUtils
import kotlinx.android.synthetic.main.kill_item.view.*

class KillViewHolder constructor (itemView: View): BaseViewHolder<LiveMessageModel>(itemView) {

    private lateinit var liveMessageModel: KillMessageModel

    override fun bindItem(item: LiveMessageModel) {
        liveMessageModel = item as KillMessageModel
        itemView.info.text = itemView.context.getString(R.string.slain_res, liveMessageModel.killer, liveMessageModel.victim)
        itemView.icon.setImageURI(ImageUtils.getImageUrl(liveMessageModel.killerTeam))
    }

}