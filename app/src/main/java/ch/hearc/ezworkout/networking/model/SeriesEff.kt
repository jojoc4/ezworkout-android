package ch.hearc.ezworkout.networking.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SeriesEff {
    @SerializedName("id")
    @Expose
    var id = 0

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("exercise_eff_id")
    @Expose
    var exerciseEffId = 0

    @SerializedName("rep")
    @Expose
    var rep = 0

    @SerializedName("weight")
    @Expose
    var weight = 0

    @SerializedName("pause")
    @Expose
    var pause = 0
}