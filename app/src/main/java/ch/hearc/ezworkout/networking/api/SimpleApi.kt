package ch.hearc.ezworkout.networking.api

import ch.hearc.ezworkout.networking.model.Exercise
import ch.hearc.ezworkout.networking.model.TrainingPlan
import ch.hearc.ezworkout.networking.model.User
import retrofit2.http.GET
import retrofit2.http.Header

interface SimpleApi {
    @GET("user")
    suspend fun getUser(@Header("Authorization") token: String): User

    @GET("exercise")
    suspend fun getExercises(@Header("Authorization") token: String): List<Exercise>

    @GET("trainingPlan")
    suspend fun getTrainingPlan(@Header("Authorization") token: String): List<TrainingPlan>
}