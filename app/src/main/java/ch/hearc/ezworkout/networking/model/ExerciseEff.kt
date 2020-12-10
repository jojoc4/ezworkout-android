package ch.hearc.ezworkout.networking.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ExerciseEff {
    @SerializedName("id")
    @Expose
    var id = 0

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("exercise_id")
    @Expose
    var exerciseId = 0

    @SerializedName("training_eff_id")
    @Expose
    var trainingEffId = 0

    @SerializedName("skipped")
    @Expose
    var skipped = 0

    @SerializedName("pause")
    @Expose
    var pause = 0

    @SerializedName("rating")
    @Expose
    var rating = 0
}