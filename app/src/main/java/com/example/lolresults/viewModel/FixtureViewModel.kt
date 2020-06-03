package com.example.lolresults.viewModel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.example.lolresults.di.getLiveApiModule
import com.example.lolresults.di.liveDataSourceModule
import com.example.lolresults.di.liveRepositoryModule
import com.example.lolresults.repository.RepositoryCallback
import com.example.lolresults.repository.fixture.FixtureRepository
import com.example.lolresults.repository.livedata.FixtureLiveMessagesRepository
import com.example.lolresults.repository.model.FixtureModel
import com.example.lolresults.repository.model.LiveMessageModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.java.KoinJavaComponent

class FixtureViewModel(private val fixtureRepository: FixtureRepository): BaseViewModel(),
    LifecycleObserver {

    private val scope = CoroutineScope(Dispatchers.IO)

    private val liveDataRepository: FixtureLiveMessagesRepository by KoinJavaComponent.inject(FixtureLiveMessagesRepository::class.java)

    val fixtureLiveData = MutableLiveData<FixtureModel>()
    val fixtureMessagesLiveData = MutableLiveData<LiveMessageModel>()

    lateinit var liveApiModule: Module
    lateinit var fixture: FixtureModel


    fun init(fixtureId: Int) {
        loadingLiveData.postValue(SHOW)
        fixtureRepository.getFixturesById(fixtureId, fixtureCallback)
    }

    private val fixtureCallback = object: RepositoryCallback<FixtureModel, Exception> {
        override fun onSuccess(result: FixtureModel) {
            fixture = result
            postFixtureData(result)
        }

        override fun onError(error: Exception) {
            postError()
        }
    }

    private fun postFixtureData(fixtureModel: FixtureModel) {
        loadingLiveData.postValue(HIDE)
        fixtureLiveData.postValue(fixtureModel)
    }

    fun subscribeToFixtureEvents() {
        if (existLiveEventsLink()) {
            subscribeToFixtureLiveEvents()
        }
    }

    private fun existLiveEventsLink() = fixture.liveEventsLink != null

    private fun subscribeToFixtureLiveEvents() {
        liveApiModule = getLiveApiModule(fixture.liveEventsLink!!)
        loadKoinModules(listOf(liveApiModule, liveDataSourceModule, liveRepositoryModule))
        liveDataRepository.subscribeToFixtureOccurrences(fixture.id, eventsCallback)
    }

    private val eventsCallback = object: RepositoryCallback<LiveMessageModel, Nothing> {
        override fun onSuccess(result: LiveMessageModel) {
            scope.launch(Dispatchers.Main) { fixtureMessagesLiveData.value = result }
        }
    }

    private fun postError() {
        loadingLiveData.postValue(HIDE)
        erroLiveData.postValue(SHOW)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        if (existLiveEventsLink())
            unloadKoinModules(listOf(liveApiModule, liveDataSourceModule, liveRepositoryModule))
    }
}