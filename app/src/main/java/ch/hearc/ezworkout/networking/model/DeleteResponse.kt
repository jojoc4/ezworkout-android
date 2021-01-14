package ch.hearc.ezworkout.networking.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DeleteResponse {
    @SerializedName("delete")
    @Expose
    var delete: String? = null

}