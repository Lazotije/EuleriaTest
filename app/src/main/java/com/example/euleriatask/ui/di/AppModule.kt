package com.example.euleriatask.ui.di

import com.example.euleriatask.data.repository.MonitoringRepo
import com.example.euleriatask.data.repository.MonitoringRepoImpl
import com.example.euleriatask.ui.viewModel.SelectDurationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    //repo
    single<MonitoringRepo> {MonitoringRepoImpl()}

    //vm
    viewModel { SelectDurationViewModel(get()) }
}