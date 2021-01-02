package ch.hearc.ezworkout.networking.repository

import android.content.SharedPreferences
import ch.hearc.ezworkout.networking.api.RetrofitInstance
import ch.hearc.ezworkout.networking.model.Exercise
import ch.hearc.ezworkout.networking.model.Training
import ch.hearc.ezworkout.networking.model.TrainingPlan
import ch.hearc.ezworkout.networking.model.User

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
}