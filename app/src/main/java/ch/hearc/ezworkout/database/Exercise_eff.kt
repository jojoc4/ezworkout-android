package ch.hearc.ezworkout.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Exercise_eff")
data class Exercise_eff(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val pauseTime: Int,
    val skipped: Boolean,
    val rating: Int
)