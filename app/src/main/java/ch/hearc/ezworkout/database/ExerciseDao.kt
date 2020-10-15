package ch.hearc.ezworkout.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExerciseDao
{
    @Query("SELECT * FROM Exercise ORDER BY id ASC")
    fun fetchAllExercise(): LiveData<List<Exercise>>
}