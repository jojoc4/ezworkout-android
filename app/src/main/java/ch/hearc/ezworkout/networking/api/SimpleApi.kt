package ch.hearc.ezworkout.networking.api

import ch.hearc.ezworkout.networking.model.Post
import ch.hearc.ezworkout.networking.model.User
import retrofit2.http.GET
import retrofit2.http.Header

interface SimpleApi {
    @GET("posts/1")
    suspend fun getPost(): Post

    @GET("test")
    suspend fun getUser(@Header("Authorization") token: String): User
}