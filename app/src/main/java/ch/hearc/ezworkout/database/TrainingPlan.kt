package ch.hearc.ezworkout.database
@Entity
data class TrainingPlan (
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?

)