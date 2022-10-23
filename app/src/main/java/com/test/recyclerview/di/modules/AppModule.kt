package com.test.recyclerview.di.modules

import android.content.Context
import com.test.recyclerview.BuildConfig
import com.test.recyclerview.data.api.Api
import com.test.recyclerview.utils.NetworkUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class AppModule{
    val appModule = module {
        single { provideNetworkHelper(androidContext()) }
        single { provideOkHttpClient() }
        single { provideRetrofitHelper(get(), BuildConfig.BASE_URL) }
        single { provideApiServices(get()) }

    }


    private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()

    private fun provideNetworkHelper(context: Context) = NetworkUtils(context)

    private fun provideRetrofitHelper(okHttpClient: OkHttpClient, baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

    private fun provideApiServices(retrofit: Retrofit): Api = retrofit.create(Api::class.java)
}


