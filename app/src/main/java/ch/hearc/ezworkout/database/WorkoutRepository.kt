package ch.hearc.ezworkout.database

import androidx.lifecycle.LiveData

class WorkoutRepository(private val workoutDao: WorkoutDao)
{
    val readAllExo: LiveData<List<Exercise>> = workoutDao.fetchAllExercises()

}