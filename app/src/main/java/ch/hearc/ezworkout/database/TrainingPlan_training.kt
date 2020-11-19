package ch.hearc.ezworkout.database


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity(primaryKeys = ["trainingPlanId","trainingId"])
data class TrainingPlan_training(
    val trainingPlanId: Long,
    val trainingId: Long,
)

data class TrainingPlanWithTraining(
    @Embedded val trainingPlan: TrainingPlan,
    @Relation(
        parentColumn = "trainingPlanId",
        entityColumn = "trainingId",
        associateBy = Junction(TrainingPlan_training::class)
    )
    val trainings: List<Training>
)