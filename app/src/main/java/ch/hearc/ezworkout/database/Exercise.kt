package ch.hearc.ezworkout.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Exercise")
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val comment: String,
    val numberOfSeries: Int,
    val repMin: Int,
    val repMax: Int,
    val pauseSerie: Int,
    val pauseExercise: Int
)