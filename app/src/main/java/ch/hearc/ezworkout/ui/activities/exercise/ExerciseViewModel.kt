package ch.hearc.ezworkout.ui.activities.exercise

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExerciseViewModel : ViewModel() {
    val exerciseId = MutableLiveData<Int>()
}
