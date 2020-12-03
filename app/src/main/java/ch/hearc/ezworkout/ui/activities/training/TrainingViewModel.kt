package ch.hearc.ezworkout.ui.activities.training

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ch.hearc.ezworkout.ui.activities.trainingPlan.TrainingContent

class TrainingViewModel : ViewModel() {

    val selected = MutableLiveData<ExerciseContent.ExerciseItem>()

    fun select(exerciseItem: ExerciseContent.ExerciseItem) {
        selected.value = exerciseItem
        Log.d(
            "Vi - Clicked on recyclerview training list item:",
            selected.value.toString()
        )
    }
}
