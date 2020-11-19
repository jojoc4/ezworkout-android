package ch.hearc.ezworkout.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WorkoutDao
{

    //******************************EXERCISES**********************************************\\

    @Query("SELECT * FROM Exercise ORDER BY id ASC")
    fun fetchAllExercises(): LiveData<List<Exercise>>


    //******************************TRAININGPLAN**********************************************\\

    /*
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTrainingPlan(trainingPlan: TrainingPlan)
*/
    /*
    @Transaction
    @Query("SELECT * FROM TrainingPlan")
    fun getTrainingPlanWithTrainings(): List<TrainingPlanWithTraining>

     */
}