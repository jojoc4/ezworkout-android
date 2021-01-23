package ch.hearc.ezworkout.ui.activities.exercise

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ch.hearc.ezworkout.networking.model.LogbookPage

class ExerciseViewModel : ViewModel() {

    val trainingPlanId = MutableLiveData<Int>()
    val trainingId = MutableLiveData<Int>()
    val exerciseId = MutableLiveData<Int>()

    val logbookPages = MutableLiveData<List<LogbookPage>>()
    val currentLBIndex = MutableLiveData<Int>()

    val chronoDurationMilis = MutableLiveData<Long>()
    val chronoEffDurationMilis = MutableLiveData<Long>()
}
