package ch.hearc.ezworkout.database


import androidx.room.Entity

@Entity(primaryKeys = ["trainingPlanId","trainingId"])
data class TrainingPlan_training(
    val trainingPlanId: Long,
    val trainingId: Long,
)