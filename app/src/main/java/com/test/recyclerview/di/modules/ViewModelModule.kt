package com.test.recyclerview.di.modules

import com.test.recyclerview.ui.main.viewModel.MainViewModel
import org.koin.dsl.module

class ViewModelModule {

    val viewModelModule = module {
        single { MainViewModel(get()) }
    }
}


