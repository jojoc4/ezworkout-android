package ch.hearc.ezworkout.networking.api

import ch.hearc.ezworkout.networking.model.*
import retrofit2.http.*

interface SimpleApi {
    @GET("user")
    suspend fun getUser(@Header("Authorization") token: String): User

    @GET("trainingPlan")
    suspend fun getTrainingPlan(@Header("Authorization") token: String): List<TrainingPlan>

    @GET("trainingPlan/{id}")
    suspend fun getTrainingPlan(@Header("Authorization") token: String, @Path("id") id: Int): TrainingPlan

    @GET("training/fromTP/{id}")
    suspend fun getTraining(@Header("Authorization") token: String, @Path("id") TPid: Integer): List<Training>

    @GET("training/{id}")
    suspend fun getTraining(@Header("Authorization") token: String, @Path("id") id: Int): Training

    @GET("exercise/fromT/{id}")
    suspend fun getExercise(@Header("Authorization") token: String, @Path("id") Tid: Integer): List<Exercise>

    @GET("exercise/{id}")
    suspend fun getExercise(@Header("Authorization") token: String, @Path("id") id: Int): Exercise

    @GET("logbookPage/fromTP/{id}")
    suspend fun getLogbookPage(@Header("Authorization") token: String, @Path("id") TPid: Integer): List<LogbookPage>

    @GET("logbookPage/{id}")
    suspend fun getLogbookPage(@Header("Authorization") token: String, @Path("id") id: Int): LogbookPage

    @GET("trainingEff/fromLBP/{id}")
    suspend fun getTrainingEff(@Header("Authorization") token: String, @Path("id") LBPid: Integer): List<TrainingEff>

    @GET("trainingEff/{id}")
    suspend fun getTrainingEff(@Header("Authorization") token: String, @Path("id") id: Int): TrainingEff

    @GET("exerciseEff/fromTE/{id}")
    suspend fun getExerciseEff(@Header("Authorization") token: String, @Path("id") TEid: Integer): List<ExerciseEff>

    @GET("exerciseEff/{id}")
    suspend fun getExerciseEff(@Header("Authorization") token: String, @Path("id") id: Int): ExerciseEff

    @GET("seriesEff/fromEE/{id}")
    suspend fun getSeriesEff(@Header("Authorization") token: String, @Path("id") EEid: Integer): List<SeriesEff>

    @GET("seriesEff/{id}")
    suspend fun getSeriesEff(@Header("Authorization") token: String, @Path("id") id: Int): SeriesEff

    @POST("logbookPage")
    suspend fun addLogbookPage(@Header("Authorization") token: String, @Query("trainigPlan") TPid: Integer): LogbookPage

    @POST("trainingEff")
    suspend fun addTrainingEff(@Header("Authorization") token: String, @Query("logbookPage") LBPid: Int, @Query("date") date: String, @Query("skipped") skipped: Int, @Query("training") Tid: Int): TrainingEff

    @POST("exerciseEff")
    suspend fun addExerciseEff(@Header("Authorization") token: String, @Query("training_eff") TEid: Int, @Query("pause") pause: Int, @Query("skipped") skipped: Int, @Query("exercise") exercise: Int, @Query("rating") rating: Int): ExerciseEff

    @POST("seriesEff")
    suspend fun addSeriesEff(@Header("Authorization") token: String, @Query("exercise_eff") EEid: Int, @Query("pause") pause: Int, @Query("rep") rep: Int, @Query("weight") weight: Int): SeriesEff
}