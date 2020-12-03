package ch.hearc.ezworkout.networking

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.hearc.ezworkout.networking.model.Post
import ch.hearc.ezworkout.networking.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel (private val repository: Repository): ViewModel()
{
    val myResponse: MutableLiveData<Post> = MutableLiveData()

    fun getPost() {
    viewModelScope.launch {
        val response = repository.getPost()
        myResponse.value = response
        }
    }
}