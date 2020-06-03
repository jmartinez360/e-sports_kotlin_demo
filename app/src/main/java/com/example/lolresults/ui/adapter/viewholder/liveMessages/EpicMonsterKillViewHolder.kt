package com.example.lolresults.ui.adapter.viewholder.liveMessages

import android.view.View
import com.example.lolresults.R
import com.example.lolresults.repository.model.EpicMonsterKillMessageModel
import com.example.lolresults.repository.model.LiveMessageModel
import com.example.lolresults.repository.model.TeamModel
import com.example.lolresults.ui.adapter.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.epic_monster_item.view.*

class EpicMonsterKillViewHolder constructor (itemView: View, teams: List<TeamModel>): BaseViewHolder<LiveMessageModel>(itemView, teams) {

    private lateinit var messageModel: EpicMonsterKillMessageModel

    override fun bindItem(item: LiveMessageModel) {
        messageModel = item as EpicMonsterKillMessageModel
        setupInfoDependingOnMonster()
    }

    private fun setupInfoDependingOnMonster() {
        val resources = getMonsterResources()
        itemView.icon.setActualImageResource(resources.iconRes)
        itemView.info.text = itemView.context.getString(resources.textRes, getTeamNameById(messageModel.killerTeamId))
        itemView.background = itemView.context.getDrawable(resources.backgroundRes)
    }

    private fun getMonsterResources(): MonsterResources {
        return when (messageModel.monsterType) {
            EpicMonsterKillMessageModel.DRAGON_TYPE -> getDragonResources()

            EpicMonsterKillMessageModel.BARON_TYPE -> getBaronResources()

            EpicMonsterKillMessageModel.RIFT_HERALD_TYPE -> setupRiftHeraldInfo()

            else -> throw Exception("Unknow monster ${messageModel.monsterType}")
        }
    }

    private fun getDragonResources(): MonsterResources {
        return when(messageModel.dragonType) {
            EpicMonsterKillMessageModel.AIR_DRAGON_TYPE ->
                MonsterResources(R.drawable.cloud_drake_icon, R.string.cloud_drake_slain, R.drawable.cloud_drake_item_drawable)

            EpicMonsterKillMessageModel.EARTH_DRAGON_TYPE ->
                MonsterResources(R.drawable.mountain_drake_icon, R.string.mountain_drake_slain, R.drawable.mountain_drake_item_drawable)

            EpicMonsterKillMessageModel.WATER_DRAGON_TYPE ->
                MonsterResources(R.drawable.ocean_drake_icon, R.string.ocean_drake_slain, R.drawable.ocean_drake_item_drawable)

            EpicMonsterKillMessageModel.FIRE_DRAGON_TYPE ->
                MonsterResources(R.drawable.infernal_drake_icon, R.string.infernal_drake_slain, R.drawable.infernal_drake_item_drawable)

            EpicMonsterKillMessageModel.ELDER_DRAGON_TYPE ->
                MonsterResources(R.drawable.elder_drake_icon, R.string.elder_drake_slain, R.drawable.elder_item_drawable)

            else ->  throw Exception("Unknow drake ${messageModel.dragonType}")
        }
    }

    private fun getBaronResources() = MonsterResources(R.drawable.baron_icon, R.string.baron_slain, R.drawable.baron_item_drawable)

    private fun setupRiftHeraldInfo() = MonsterResources(R.drawable.herald_icon, R.string.herald_slain, R.drawable.herald_item_drawable)


    private inner class MonsterResources constructor(val iconRes: Int, val textRes: Int, val backgroundRes: Int)


}