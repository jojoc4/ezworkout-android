package ch.hearc.ezworkout.networking.api

import ch.hearc.ezworkout.networking.model.Post
import retrofit2.http.GET

interface SimpleApi {
    @GET("posts/1")
    suspend fun getPost(): Post
}