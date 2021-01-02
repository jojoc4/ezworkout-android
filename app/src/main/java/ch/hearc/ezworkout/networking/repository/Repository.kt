package ch.hearc.ezworkout.networking.repository

import android.content.SharedPreferences
import ch.hearc.ezworkout.networking.api.RetrofitInstance
import ch.hearc.ezworkout.networking.model.*

class Repository(private val sharedPref: SharedPreferences) {

    private val token = "Bearer " + sharedPref.getString("api", "")

    suspend fun getUser() : User {
        return  RetrofitInstance.api.getUser(token)
    }

    suspend fun getTrainingPlan() : List<TrainingPlan> {
        return  RetrofitInstance.api.getTrainingPlan(token)
    }

    suspend fun getTraining(TPid: Integer) : List<Training> {
        return  RetrofitInstance.api.getTraining(token, TPid)
    }

    suspend fun getExercise(Tid: Integer) : List<Exercise> {
        return  RetrofitInstance.api.getExercise(token, Tid)
    }

    suspend fun getLogbookPage(TPid: Integer) : List<LogbookPage> {
        return  RetrofitInstance.api.getLogbookPage(token, TPid)
    }
}