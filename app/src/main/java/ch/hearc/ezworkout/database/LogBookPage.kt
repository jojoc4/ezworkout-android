package ch.hearc.ezworkout.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LogBookPage")
data class LogBookPage(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
)