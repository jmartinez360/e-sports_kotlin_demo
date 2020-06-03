package com.example.lolresults.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lolresults.R
import com.example.lolresults.repository.model.FixtureModel
import com.example.lolresults.ui.adapter.FixtureAdapter
import com.example.lolresults.ui.adapter.ItemClickListener
import com.example.lolresults.viewModel.FixtureListViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.activity_fixture_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class FixtureListActivity : AppCompatActivity(), ItemClickListener<FixtureModel> {

    private val fixtureViewModel: FixtureListViewModel by viewModel()
    private val adapter by lazy { FixtureAdapter(itemClickListener = this) }
    private val layoutManager by lazy { LinearLayoutManager(this, RecyclerView.VERTICAL, false) }
    private val datePicker by lazy { MaterialDatePicker.Builder.dateRangePicker().build() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fixture_list)
        setupFixtureList()
        setupLiveData()
        setupDateButton()

        fixtureViewModel.init()
    }

    private fun setupFixtureList() {
        recycler.layoutManager = layoutManager
        recycler.adapter = adapter
    }

    override fun onItemClick(item: FixtureModel) {
        startActivity(FixtureDetailActivity.getIntent(this, item.id))
    }

    private fun setupLiveData() {
        fixtureViewModel.fixturesLiveData.observe(this, Observer {  showFixtureList(it) })
    }

    private fun showFixtureList(fixtureList: List<FixtureModel>) {
        adapter.addFixtures(fixtureList)
        adapter.notifyDataSetChanged()
    }

    private fun setupDateButton() {
        dateButton.setOnClickListener {  showDatePicker() }
    }

    private fun showDatePicker() {
        datePicker.show(supportFragmentManager, datePicker.toString())
        datePicker.addOnPositiveButtonClickListener {
            fixtureViewModel.getFixtures(it.first!!, it.second!!)
        }
    }

}
