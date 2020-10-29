package ch.hearc.ezworkout.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface WorkoutDao
{

    //******************************EXERCISES**********************************************\\

    @Query("SELECT * FROM Exercise ORDER BY id ASC")
    fun fetchAllExercises(): LiveData<List<Exercise>>
}