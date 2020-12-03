package ch.hearc.ezworkout.networking.repository

import ch.hearc.ezworkout.networking.api.RetrofitInstance
import ch.hearc.ezworkout.networking.model.Post

class Repository {

    suspend fun getPost() : Post {
        return  RetrofitInstance.api.getPost()
    }
}