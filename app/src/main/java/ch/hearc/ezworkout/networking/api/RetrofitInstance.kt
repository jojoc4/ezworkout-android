package ch.hearc.ezworkout.networking.api

import ch.hearc.ezworkout.networking.utils.LogJsonInterceptor
import ch.hearc.ezworkout.networking.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    private val retrofit by lazy {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(LogJsonInterceptor())
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    val api: SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }
}