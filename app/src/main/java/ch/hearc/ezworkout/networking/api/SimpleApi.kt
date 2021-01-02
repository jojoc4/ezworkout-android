package ch.hearc.ezworkout.networking.api

import ch.hearc.ezworkout.networking.model.*
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

    @GET("exercise/fromT/{id}")
    suspend fun getExercise(@Header("Authorization") token: String, @Path("id") Tid: Integer): List<Exercise>

    @GET("logbookPage/fromTP/{id}")
    suspend fun getLogbookPage(@Header("Authorization") token: String, @Path("id") TPid: Integer): List<LogbookPage>

    @GET("trainingEff/fromLBP/{id}")
    suspend fun getTrainingEff(@Header("Authorization") token: String, @Path("id") LBPid: Integer): List<TrainingEff>

    @GET("exerciseEff/fromTE/{id}")
    suspend fun getExerciseEff(@Header("Authorization") token: String, @Path("id") TEid: Integer): List<ExerciseEff>

    @GET("seriesEff/fromEE/{id}")
    suspend fun getSeriesEff(@Header("Authorization") token: String, @Path("id") EEid: Integer): List<SeriesEff>
}