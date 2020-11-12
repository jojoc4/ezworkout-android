package ch.hearc.ezworkout.database

import androidx.lifecycle.LiveData

class WorkoutRepository(private val workoutDao: WorkoutDao)
{
    val readAllExo: LiveData<List<Exercise>> = workoutDao.fetchAllExercises()

    /*
    suspend fun addTrainingPlan(trainingPlan: TrainingPlan){
        workoutDao.addTrainingPlan(trainingPlan)
    }*/

}