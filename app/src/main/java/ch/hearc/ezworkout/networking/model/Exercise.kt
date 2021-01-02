package ch.hearc.ezworkout.networking.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Exercise {
    @SerializedName("id")
    @Expose
    var id = 0

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("comment")
    @Expose
    var comment: String? = null

    @SerializedName("nbSerie")
    @Expose
    var nbSerie = 0

    @SerializedName("repMin")
    @Expose
    var repMin = 0

    @SerializedName("repMax")
    @Expose
    var repMax = 0

    @SerializedName("pauseSerie")
    @Expose
    var pauseSerie = 0

    @SerializedName("pauseExercise")
    @Expose
    var pauseExercise = 0
}