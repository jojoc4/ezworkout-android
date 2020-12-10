package ch.hearc.ezworkout.networking.repository

import ch.hearc.ezworkout.networking.api.RetrofitInstance
import ch.hearc.ezworkout.networking.model.Post
import ch.hearc.ezworkout.networking.model.User

class Repository {

    suspend fun getPost() : Post {
        return  RetrofitInstance.api.getPost()
    }

    suspend fun getUser() : User {
        return  RetrofitInstance.api.getUser("Bearer 2|UNTDm9isGqzCxMgqIKZWxKMVBXQU1bLcbmWIQJFe")
    }
}