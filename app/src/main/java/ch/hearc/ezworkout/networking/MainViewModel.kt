package ch.hearc.ezworkout.networking

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.hearc.ezworkout.networking.model.Exercise
import ch.hearc.ezworkout.networking.model.Post
import ch.hearc.ezworkout.networking.model.TrainingPlan
import ch.hearc.ezworkout.networking.model.User
import ch.hearc.ezworkout.networking.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel (private val repository: Repository): ViewModel()
{
    val myResponse2: MutableLiveData<Post> = MutableLiveData()

    fun getPost() {
    viewModelScope.launch {
        val response = repository.getPost()
        myResponse2.value = response
        }
    }

    val myResponse: MutableLiveData<User> = MutableLiveData()

    fun getUser() {
        viewModelScope.launch {
            val response = repository.getUser()
            myResponse.value = response
        }
    }

    val exerciseResponse: MutableLiveData<List<Exercise>> = MutableLiveData()

    fun getExercises() {
        viewModelScope.launch {
            val response = repository.getExercises()
            exerciseResponse.value = response
        }
    }

    val trainingPlanResponse: MutableLiveData<List<TrainingPlan>> = MutableLiveData()

    fun getTrainingPlan() {
        viewModelScope.launch {
            val response = repository.getTrainingPlan()
            trainingPlanResponse.value = response
        }
    }
}