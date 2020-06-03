package com.example.lolresults.di

import com.example.lolresults.viewModel.FixtureListViewModel
import com.example.lolresults.viewModel.FixtureViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { FixtureListViewModel(get()) }
    viewModel { FixtureViewModel(get()) }
}