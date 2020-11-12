package ch.hearc.ezworkout.database

import androidx.room.Entity

@Entity(primaryKeys = ["trainingId","exerciseId"])
data class Training_exercise(
    val trainingId: Long,
    val exerciseId: Long,
)