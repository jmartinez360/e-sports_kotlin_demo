package com.example.lolresults.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {

    val loadingLiveData = MutableLiveData<Boolean>()
    val erroLiveData = MutableLiveData<Boolean>()

    companion object {
        const val SHOW = true
        const val HIDE = false
    }
}