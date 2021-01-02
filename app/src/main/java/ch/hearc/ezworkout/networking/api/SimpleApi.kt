package ch.hearc.ezworkout.networking.api

import ch.hearc.ezworkout.networking.model.Training
import ch.hearc.ezworkout.networking.model.TrainingPlan
import ch.hearc.ezworkout.networking.model.User
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface SimpleApi {
    @GET("user")
    suspend fun getUser(@Header("Authorization") token: String): User

    @GET("trainingPlan")
    suspend fun getTrainingPlan(@Header("Authorization") token: String): List<TrainingPlan>

    @GET("training/fromTP/{id}")
    suspend fun getTraining(@Header("Authorization") token: String, @Path("id") TPid: Integer): List<Training>
}