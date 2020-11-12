package ch.hearc.ezworkout.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TrainingPlan")
data class TrainingPlan(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
)