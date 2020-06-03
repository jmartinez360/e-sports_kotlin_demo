package com.example.lolresults.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lolresults.R
import com.example.lolresults.repository.model.FixtureModel
import com.example.lolresults.repository.model.LiveMessageModel
import com.example.lolresults.repository.model.TeamModel
import com.example.lolresults.ui.adapter.LiveMessagesAdapter
import com.example.lolresults.utils.ImageUtils
import com.example.lolresults.viewModel.FixtureViewModel
import kotlinx.android.synthetic.main.activity_fixture_detail.*
import kotlinx.android.synthetic.main.teams_header.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FixtureDetailActivity : AppCompatActivity() {

    private val viewModel: FixtureViewModel by viewModel()
    private val layoutManager by lazy { LinearLayoutManager(this, RecyclerView.VERTICAL, false) }

    private lateinit var adapter: LiveMessagesAdapter

    private lateinit var fixture: FixtureModel
    private lateinit var leftTeam: TeamModel
    private lateinit var rightTeam: TeamModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fixture_detail)
        lifecycle.addObserver(viewModel)

        setupObservers()

        viewModel.init(intent.getIntExtra(FIXTURE_ID, 0))
    }

    private fun setupObservers() {
        setupFixtureObserver()
        setupFixtureMessagesObserver()
    }

    private fun setupFixtureObserver() {
        viewModel.fixtureLiveData.observe(this, Observer { setupFixture(it) })
    }

    private fun setupFixture(fixture: FixtureModel) {
        this.fixture = fixture
        this.leftTeam = fixture.teams[0]
        this.rightTeam = fixture.teams[1]
        setupTeams()
        setupLiveMessagesAdapter()
        viewModel.subscribeToFixtureEvents()
    }

    private fun setupTeams() {
        setupTeamNames()
        setupResult()
        setupShields()
    }

    private fun setupTeamNames() {
        leftTeamName.text = leftTeam.name
        rightTeamName.text = rightTeam.name
    }

    private fun setupResult() {
        result.text = getString(R.string.fixture_score, leftTeam.score, rightTeam.score)
        setupFixtureFormat()
    }

    private fun setupFixtureFormat() {
        matchFormat.text = getString(R.string.fixture_format, fixture.format.value)
    }

    private fun setupShields() {
        leftTeamShield.setImageURI(ImageUtils.getImageUrl(leftTeam.id))
        rightTeamShield.setImageURI(ImageUtils.getImageUrl(rightTeam.id))
    }

    private fun setupLiveMessagesAdapter() {
        adapter = LiveMessagesAdapter(fixture.teams)

        messagesRecycler.layoutManager = layoutManager
        messagesRecycler.adapter = adapter
    }

    private fun setupFixtureMessagesObserver() {
        viewModel.fixtureMessagesLiveData.observe(this, Observer { addMessage(it) })
    }

    private fun addMessage(message: LiveMessageModel) {
        adapter.addMessage(message)
        noResults.visibility = View.GONE
    }

    companion object {

        private const val FIXTURE_ID = "FIXTURE_ID"

        fun getIntent(context: Context,  fixtureId: Int): Intent {
            val intent = Intent(context, FixtureDetailActivity::class.java)
            intent.putExtra(FIXTURE_ID, fixtureId)
            return intent
        }
    }
}
