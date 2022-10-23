package com.test.recyclerview.ui.main.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.test.recyclerview.data.models.TrendingResponse
import com.test.recyclerview.data.repository.MainRepository
import com.test.recyclerview.utils.ApiStatus

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    init {
        mainRepository.getAllTrending()
    }


    fun onRefresh() {
        mainRepository.getAllTrending()
    }



    val trendingData: LiveData<ApiStatus<TrendingResponse>>
        get() = mainRepository.trendingData
}