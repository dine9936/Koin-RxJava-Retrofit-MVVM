package com.test.recyclerview.di.modules

import com.test.recyclerview.data.repository.MainRepository
import org.koin.dsl.module

class RepositoryModule {
    val repositoryModule = module {
        single { MainRepository(get(), get()) }
    }
}


