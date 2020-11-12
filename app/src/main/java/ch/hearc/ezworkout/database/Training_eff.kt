package ch.hearc.ezworkout.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Training_eff")
data class Training_eff(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val date: Long,
    val skipped:Boolean,
    val trainingId: Long
)