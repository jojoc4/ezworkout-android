package ch.hearc.ezworkout.ui.tracking.training

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TrainingViewModel : ViewModel() {

    val currentLBPid = MutableLiveData<Int>()
    val trainingEffId = MutableLiveData<Int>()
    val trainingPlanId = MutableLiveData<Int>()
    val trainingId = MutableLiveData<Int>()

    val selected = MutableLiveData<ExerciseContent.ExerciseItem>()

    fun select(exerciseItem: ExerciseContent.ExerciseItem) {
        selected.value = exerciseItem
        Log.d(
            "Vi - Clicked on recyclerview training list item:",
            selected.value.toString()
        )
    }
}
