package ch.hearc.ezworkout.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Serie_eff")
data class Serie_eff(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val rep: Int,
    val weight: Float,
    val pauseTime: Int
)