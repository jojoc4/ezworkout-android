package ch.hearc.ezworkout.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Training_eff")
data class Training_eff(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val date: Long,
    val skipped:Boolean
)