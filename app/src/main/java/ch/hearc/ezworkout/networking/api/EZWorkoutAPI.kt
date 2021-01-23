package ch.hearc.ezworkout.networking.api

import ch.hearc.ezworkout.networking.model.*
import retrofit2.http.*

interface EZWorkoutAPI {
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

    @GET("trainingEff/fromLBPAndTraining/{idLBP}/{idTr}")
    suspend fun getTrainingEff(@Header("Authorization") token: String, @Path("idLBP") idLBP: Int, @Path("idTr") idTr: Int): TrainingEff

    @GET("exerciseEff/fromTE/{id}")
    suspend fun getExerciseEff(@Header("Authorization") token: String, @Path("id") TEid: Integer): List<ExerciseEff>

    @GET("exerciseEff/{id}")
    suspend fun getExerciseEff(@Header("Authorization") token: String, @Path("id") id: Int): ExerciseEff

    @GET("exerciseEff/fromTEAndExercise/{idETr}/{idEx}")
    suspend fun getExerciseEff(@Header("Authorization") token: String, @Path("idETr") idETr: Int, @Path("idEx") idEx: Int): ExerciseEff

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

    @POST("trainingPlan")
    suspend fun addTrainingPlan(@Header("Authorization") token: String, @Query("name") name: String): TrainingPlan

    @POST("training")
    suspend fun addTraining(@Header("Authorization") token: String, @Query("name") name: String, @Query("trainingPlan") TPid: Int): Training

    @POST("exercise")
    suspend fun addExercise(@Header("Authorization") token: String, @Query("name") name: String, @Query("comment") comment: String, @Query("nbSerie") nbSerie: Int, @Query("repMin") repMin: Int, @Query("repMax") repMax: Int, @Query("pauseSerie") pauseSerie: Int, @Query("pauseExercise") pauseExercise: Int, @Query("training") trid: Int): Exercise


    @PUT("trainingPlan/{id}/")
    suspend fun updateTrainingPlan(@Header("Authorization") token: String, @Path("id") id: Int, @Query("name") name: String): TrainingPlan

    @PUT("training/{id}/")
    suspend fun updateTraining(@Header("Authorization") token: String, @Path("id") id: Int, @Query("name") name: String): Training

    @PUT("exercise/{id}/")
    suspend fun updateExercise(@Header("Authorization") token: String, @Path("id") id: Int, @Query("name") name: String, @Query("comment") comment: String, @Query("nbSerie") nbSerie: Int, @Query("repMin") repMin: Int, @Query("repMax") repMax: Int, @Query("pauseSerie") pauseSerie: Int, @Query("pauseExercise") pauseExercise: Int): Exercise


    @DELETE("trainingPlan/{id}")
    suspend fun delTrainingPlan(@Header("Authorization") token: String, @Path("id") id: Int): DeleteResponse

    @POST("training/{id}/removeFromTrainingPlan")
    suspend fun delTraining(@Header("Authorization") token: String, @Path("id") id: Int, @Query("trainingPlan") TPid: Int)

    @POST("exercise/{id}/removeFromTraining")
    suspend fun delExercise(@Header("Authorization") token: String, @Path("id") id: Int, @Query("training") trid: Int)
}