package com.test.recyclerview.data.api

import com.test.recyclerview.data.models.TrendingResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Header

interface Api {
    @GET("trending")
    fun getAllTrending(@Header("X-RapidAPI-Key") api_key: String): Observable<TrendingResponse>
}