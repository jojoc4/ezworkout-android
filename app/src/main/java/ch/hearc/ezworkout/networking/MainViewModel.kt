package ch.hearc.ezworkout.networking

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.hearc.ezworkout.networking.model.*
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

    val exerciseResponse: MutableLiveData<List<Exercise>> = MutableLiveData()
    fun getExercise(Tid: Integer) {
        viewModelScope.launch {
            val response = repository.getExercise(Tid)
            exerciseResponse.value = response
        }
    }

    val logbookPageResponse: MutableLiveData<List<LogbookPage>> = MutableLiveData()
    fun getLogbookPage(TPid: Integer) {
        viewModelScope.launch {
            val response = repository.getLogbookPage(TPid)
            logbookPageResponse.value = response
        }
    }

    val trainingEffResponse: MutableLiveData<List<TrainingEff>> = MutableLiveData()
    fun getTrainingEff(LBPid: Integer) {
        viewModelScope.launch {
            val response = repository.getTrainingEff(LBPid)
            trainingEffResponse.value = response
        }
    }

    val exerciseEffResponse: MutableLiveData<List<ExerciseEff>> = MutableLiveData()
    fun getExerciseEff(TEid: Integer) {
        viewModelScope.launch {
            val response = repository.getExerciseEff(TEid)
            exerciseEffResponse.value = response
        }
    }

    val seriesEffResponse: MutableLiveData<List<SeriesEff>> = MutableLiveData()
    fun getSeriesEff(EEid: Integer) {
        viewModelScope.launch {
            val response = repository.getSeriesEff(EEid)
            seriesEffResponse.value = response
        }
    }

    val newlogbookPageResponse: MutableLiveData<LogbookPage> = MutableLiveData()
    fun addLogbookPage(logbookPage: LogbookPage) {
        viewModelScope.launch {
            val response = repository.addLogbookPage(logbookPage)
            newlogbookPageResponse.value = response
        }
    }

    val newTrainingEffResponse: MutableLiveData<TrainingEff> = MutableLiveData()
    fun addTrainingEff(trainingEff: TrainingEff) {
        viewModelScope.launch {
            val response = repository.addTrainingEff(trainingEff)
            newTrainingEffResponse.value = response
        }
    }
}