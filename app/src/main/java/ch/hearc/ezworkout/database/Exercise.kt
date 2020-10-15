package ch.hearc.ezworkout.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Exercise")
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val comment: String,
    val numberOfSeries: Int,
    val repetitionMin: Int,
    val repetitionMax: Int,
    val breakTimeSeries: Int,
    val breakTimeExercise: Int,
)