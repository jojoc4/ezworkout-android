package ch.hearc.ezworkout.networking.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("id")
    @Expose
    var id = 0

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("email_verified_at")
    @Expose
    var emailVerifiedAt: Any? = null

    @SerializedName("current_team_id")
    @Expose
    var currentTeamId: Any? = null

    @SerializedName("profile_photo_path")
    @Expose
    var profilePhotoPath: Any? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("profile_photo_url")
    @Expose
    var profilePhotoUrl: String? = null
}