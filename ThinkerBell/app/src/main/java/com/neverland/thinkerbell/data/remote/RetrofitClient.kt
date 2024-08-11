package com.neverland.thinkerbell.data.remote

import com.google.gson.GsonBuilder
import com.neverland.thinkerbell.BuildConfig
import com.neverland.thinkerbell.data.utils.PrettyJsonLogger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var instance : Retrofit? = null
    private val gson = GsonBuilder().setLenient().create()

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor(PrettyJsonLogger()).apply {
                if(BuildConfig.DEBUG) setLevel(HttpLoggingInterceptor.Level.BODY)
                else setLevel(HttpLoggingInterceptor.Level.NONE)
            }
        )
        .build()

    fun getInstance() : Retrofit {
        if(instance == null){
            instance = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return instance!!
    }
}