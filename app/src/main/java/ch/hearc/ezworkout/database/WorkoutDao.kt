package ch.hearc.ezworkout.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface WorkoutDao
{

    //******************************EXERCISES**********************************************\\

    @Query("SELECT * FROM Exercise ORDER BY id ASC")
    fun fetchAllExercises(): LiveData<List<Exercise>>


    //******************************LOGBOOKPAGES-TRAININGPLAN**********************************************\\

    @Transaction
    @Query("SELECT * FROM TrainingPlan")
    fun logBookPagesWithtrainingPlan(): List<logBookPagesWithtrainingPlan>


}