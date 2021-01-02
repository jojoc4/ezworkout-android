package ch.hearc.ezworkout.networking.repository

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import ch.hearc.ezworkout.networking.api.RetrofitInstance
import ch.hearc.ezworkout.networking.model.Exercise
import ch.hearc.ezworkout.networking.model.Post
import ch.hearc.ezworkout.networking.model.TrainingPlan
import ch.hearc.ezworkout.networking.model.User

class Repository(private val sharedPref: SharedPreferences) {

    private val token = "Bearer " + sharedPref.getString("api", "")

    suspend fun getPost() : Post {
        return  RetrofitInstance.api.getPost()
    }

    suspend fun getUser() : User {
        return  RetrofitInstance.api.getUser(token)
    }

    suspend fun getExercises() : List<Exercise> {
        return  RetrofitInstance.api.getExercises(token)
    }

    suspend fun getTrainingPlan() : List<TrainingPlan> {
        return  RetrofitInstance.api.getTrainingPlan(token)
    }
}