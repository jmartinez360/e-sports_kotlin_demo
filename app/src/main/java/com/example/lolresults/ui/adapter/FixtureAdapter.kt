package com.example.lolresults.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lolresults.R
import com.example.lolresults.repository.model.FixtureModel
import com.example.lolresults.ui.adapter.viewholder.FixtureViewHolder

class FixtureAdapter constructor(private val itemClickListener: ItemClickListener<FixtureModel>):
    RecyclerView.Adapter<FixtureViewHolder>() {

    private val fixtureItems = mutableListOf<FixtureModel>()

    fun addFixtures(fixtures: List<FixtureModel>) {
        fixtureItems.clear()
        fixtureItems.addAll(fixtures)
    }

    override fun getItemCount() = fixtureItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FixtureViewHolder {
        return FixtureViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fixture_item, parent, false), itemClickListener)
    }

    override fun onBindViewHolder(holder: FixtureViewHolder, position: Int) {
        holder.bindItem(fixtureItems[position])
    }
}