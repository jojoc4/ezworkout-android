package ch.hearc.ezworkout.ui.tracking.trainingPlan

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TrainingPlanViewModel : ViewModel() {

    val currentLBPid = MutableLiveData<Int>()
    val trainingPlanId = MutableLiveData<Int>()
    val selected = MutableLiveData<TrainingContent.TrainingItem>()

    fun select(trainingItem: TrainingContent.TrainingItem) {
        selected.value = trainingItem
        Log.d(
            "Vi - Clicked on recyclerview training list item:",
            selected.value.toString()
        )
    }
}