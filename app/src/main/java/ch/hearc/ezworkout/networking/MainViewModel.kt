package ch.hearc.ezworkout.networking

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.hearc.ezworkout.networking.model.Exercise
import ch.hearc.ezworkout.networking.model.Training
import ch.hearc.ezworkout.networking.model.TrainingPlan
import ch.hearc.ezworkout.networking.model.User
import ch.hearc.ezworkout.networking.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel (private val repository: Repository): ViewModel()
{

    val userResponse: MutableLiveData<User> = MutableLiveData()
    fun getUser() {
        viewModelScope.launch {
            val response = repository.getUser()
            userResponse.value = response
        }
    }

    val trainingPlanResponse: MutableLiveData<List<TrainingPlan>> = MutableLiveData()
    fun getTrainingPlan() {
        viewModelScope.launch {
            val response = repository.getTrainingPlan()
            trainingPlanResponse.value = response
        }
    }

    val trainingResponse: MutableLiveData<List<Training>> = MutableLiveData()
    fun getTraining(TPid: Integer) {
        viewModelScope.launch {
            val response = repository.getTraining(TPid)
            trainingResponse.value = response
        }
    }
}