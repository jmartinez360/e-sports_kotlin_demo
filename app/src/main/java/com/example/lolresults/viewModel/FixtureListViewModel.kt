package com.example.lolresults.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.lolresults.repository.RepositoryCallback
import com.example.lolresults.repository.fixture.FixtureRepository
import com.example.lolresults.repository.model.FixtureModel

class FixtureListViewModel(private val fixtureRepository: FixtureRepository): BaseViewModel() {

    val fixturesLiveData = MutableLiveData<List<FixtureModel>>()

    fun init() {
        getFixtures(1571235535000, 1590848335000)
    }

    fun getFixtures(from: Long, to: Long) {
        loadingLiveData.postValue(SHOW)
        fixtureRepository.getFixtures(from, to, fixtureCallback)
    }

    private val fixtureCallback = object : RepositoryCallback<List<FixtureModel>, Exception> {
        override fun onSuccess(result: List<FixtureModel>) {
            postFixtureValues(result)
        }

        override fun onError(error: Exception) {
            postError()
        }
    }

    private fun postFixtureValues(result: List<FixtureModel>) {
        loadingLiveData.postValue(HIDE)
        fixturesLiveData.postValue(result)
    }

    private fun postError() {
        loadingLiveData.postValue(HIDE)
        erroLiveData.postValue(SHOW)
    }
}
