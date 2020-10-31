package ch.hearc.ezworkout.database

import androidx.room.Relation
import androidx.room.Embedded

data class logBookPagesWithtrainingPlan(
    @Embedded val trainingPlan: TrainingPlan,
    @Relation(
        parentColumn = "id",
        entityColumn = "trainingPlanId"
    )
    val playlists: List<LogBookPage>
)
