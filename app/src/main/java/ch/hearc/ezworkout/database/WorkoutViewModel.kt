package ch.hearc.ezworkout.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class WorkoutViewModel(application: Application) : AndroidViewModel(application){

    private val readAllExo: LiveData<List<Exercise>>
    private val repository: WorkoutRepository

    init {
        val workoutDao = WorkoutDatabase.getDatabase(application).workoutDao()
        repository = WorkoutRepository(workoutDao)
        readAllExo = repository.readAllExo
    }
}