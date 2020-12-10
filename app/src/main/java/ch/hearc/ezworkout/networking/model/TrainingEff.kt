package ch.hearc.ezworkout.networking.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TrainingEff {
    @SerializedName("id")
    @Expose
    var id = 0

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("logbook_page_id")
    @Expose
    var logbookPageId = 0

    @SerializedName("training_id")
    @Expose
    var trainingId = 0

    @SerializedName("date")
    @Expose
    var date: String? = null

    @SerializedName("skipped")
    @Expose
    var skipped = 0
}