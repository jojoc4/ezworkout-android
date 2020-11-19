package ch.hearc.ezworkout.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Training")
data class Training(
    @PrimaryKey(autoGenerate = true)
    val trainingId: Long,
    val name: String,
)