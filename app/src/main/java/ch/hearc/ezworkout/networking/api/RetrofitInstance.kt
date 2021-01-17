package ch.hearc.ezworkout.networking.api

import ch.hearc.ezworkout.networking.utils.LogJsonInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    var url = "https://ezw.dev.jojoc4.ch"

    private val retrofit by lazy {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(LogJsonInterceptor())
        Retrofit.Builder()
            .baseUrl("$url/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    val api: EZWorkoutAPI by lazy {
        retrofit.create(EZWorkoutAPI::class.java)
    }
}