package com.test.recyclerview.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.recyclerview.BuildConfig
import com.test.recyclerview.data.models.TrendingResponse
import com.test.recyclerview.data.api.Api
import com.test.recyclerview.utils.ApiStatus
import com.test.recyclerview.utils.NetworkUtils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainRepository(private val api: Api, private val networkUtils: NetworkUtils) {

    private var _trendingData = MutableLiveData<ApiStatus<TrendingResponse>>()

    val trendingData:LiveData<ApiStatus<TrendingResponse>>
    get() = _trendingData


    fun getAllTrending(){
        if (networkUtils.isInternetAvailable()){
            _trendingData.postValue(ApiStatus.Loading())
            val response = api.getAllTrending(BuildConfig.API_KEY)
            response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _trendingData.postValue(ApiStatus.Success(it))
                },{
                    _trendingData.postValue(ApiStatus.Exception(it))
                })
        }else{
            _trendingData.postValue(ApiStatus.Error())
        }

    }




}