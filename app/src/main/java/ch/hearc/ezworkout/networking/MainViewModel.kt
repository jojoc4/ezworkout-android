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

    val oneTrainingPlanResponse: MutableLiveData<TrainingPlan> = MutableLiveData()
    fun getTrainingPlan(id: Int) {
        viewModelScope.launch {
            val response = repository.getTrainingPlan(id)
            oneTrainingPlanResponse.value = response
        }
    }

    val trainingResponse: MutableLiveData<List<Training>> = MutableLiveData()
    fun getTraining(TPid: Integer) {
        viewModelScope.launch {
            val response = repository.getTraining(TPid)
            trainingResponse.value = response
        }
    }

    val oneTrainingResponse: MutableLiveData<Training> = MutableLiveData()
    fun getTraining(id: Int) {
        viewModelScope.launch {
            val response = repository.getTraining(id)
            oneTrainingResponse.value = response
        }
    }

    val exerciseResponse: MutableLiveData<List<Exercise>> = MutableLiveData()
    fun getExercise(Tid: Integer) {
        viewModelScope.launch {
            val response = repository.getExercise(Tid)
            exerciseResponse.value = response
        }
    }

    val oneExerciseResponse: MutableLiveData<Exercise> = MutableLiveData()
    fun getExercise(id: Int) {
        viewModelScope.launch {
            val response = repository.getExercise(id)
            oneExerciseResponse.value = response
        }
    }

    val logbookPageResponse: MutableLiveData<List<LogbookPage>> = MutableLiveData()
    fun getLogbookPage(TPid: Integer) {
        viewModelScope.launch {
            val response = repository.getLogbookPage(TPid)
            logbookPageResponse.value = response
        }
    }

    val oneLogbookPageResponse: MutableLiveData<LogbookPage> = MutableLiveData()
    fun getLogbookPage(id: Int) {
        viewModelScope.launch {
            val response = repository.getLogbookPage(id)
            oneLogbookPageResponse.value = response
        }
    }

    val trainingEffResponse: MutableLiveData<List<TrainingEff>> = MutableLiveData()
    fun getTrainingEff(LBPid: Integer) {
        viewModelScope.launch {
            val response = repository.getTrainingEff(LBPid)
            trainingEffResponse.value = response
        }
    }

    val oneTrainingEffResponse: MutableLiveData<TrainingEff> = MutableLiveData()
    fun getTrainingEff(id: Int) {
        viewModelScope.launch {
            val response = repository.getTrainingEff(id)
            oneTrainingEffResponse.value = response
        }
    }

    val exerciseEffResponse: MutableLiveData<List<ExerciseEff>> = MutableLiveData()
    fun getExerciseEff(TEid: Integer) {
        viewModelScope.launch {
            val response = repository.getExerciseEff(TEid)
            exerciseEffResponse.value = response
        }
    }

    val oneExerciseEffResponse: MutableLiveData<ExerciseEff> = MutableLiveData()
    fun getExerciseEff(id: Int) {
        viewModelScope.launch {
            val response = repository.getExerciseEff(id)
            oneExerciseEffResponse.value = response
        }
    }

    val seriesEffResponse: MutableLiveData<List<SeriesEff>> = MutableLiveData()
    fun getSeriesEff(EEid: Integer) {
        viewModelScope.launch {
            val response = repository.getSeriesEff(EEid)
            seriesEffResponse.value = response
        }
    }

    val oneSeriesEffResponse: MutableLiveData<SeriesEff> = MutableLiveData()
    fun getSeriesEff(id: Int) {
        viewModelScope.launch {
            val response = repository.getSeriesEff(id)
            oneSeriesEffResponse.value = response
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

    val newExerciseEffResponse: MutableLiveData<ExerciseEff> = MutableLiveData()
    fun addExerciseEff(exerciseEff: ExerciseEff) {
        viewModelScope.launch {
            val response = repository.addExerciseEff(exerciseEff)
            newExerciseEffResponse.value = response
        }
    }

    val newSeriesEffResponse: MutableLiveData<SeriesEff> = MutableLiveData()
    fun addSeriesEff(seriesEff: SeriesEff) {
        viewModelScope.launch {
            val response = repository.addSeriesEff(seriesEff)
            newSeriesEffResponse.value = response
        }
    }


    val updateTrainingPlanResponse: MutableLiveData<TrainingPlan> = MutableLiveData()
    fun updateTrainingPlan(tp: TrainingPlan) {
        viewModelScope.launch {
            val response = repository.updateTrainingPlan(tp)
            updateTrainingPlanResponse.value = response
        }
    }

    val updateTrainingResponse: MutableLiveData<Training> = MutableLiveData()
    fun updateTraining(tr: Training) {
        viewModelScope.launch {
            val response = repository.updateTraining(tr)
            updateTrainingResponse.value = response
        }
    }

    val updateExerciseResponse: MutableLiveData<Exercise> = MutableLiveData()
    fun updateExercise(ex: Exercise) {
        viewModelScope.launch {
            val response = repository.updateExercise(ex)
            updateExerciseResponse.value = response
        }
    }


    val delTrainingPlanResponse: MutableLiveData<DeleteResponse> = MutableLiveData()
    fun delTrainingPlan(tp: TrainingPlan) {
        viewModelScope.launch {
            val response = repository.deleteTrainingPlan(tp)
            delTrainingPlanResponse.value = response
        }
    }
}