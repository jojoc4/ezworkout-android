package ch.hearc.ezworkout.ui.activities.exercise

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ch.hearc.ezworkout.networking.model.LogbookPage

class ExerciseViewModel : ViewModel() {

    val trainingPlanId = MutableLiveData<Int>()
    val trainingId = MutableLiveData<Int>()
    val trainingEffId = MutableLiveData<Int>()
    val exerciseId = MutableLiveData<Int>()
    val exerciseEffId = MutableLiveData<Int>()

    val logbookPages = MutableLiveData<List<LogbookPage>>()
    val currentLBIndex = MutableLiveData<Int>()

    val currentLBPId = MutableLiveData<Int>()

    val serieCount = MutableLiveData<Int>()
    val serieCountEff = MutableLiveData<Int>()

    val chronoDurationMilis = MutableLiveData<Long>()

    val currentSeriePos = MutableLiveData<Int>()
    val currentSerieIndex = MutableLiveData<Int>()

    val chronoEffDurationMilis = MutableLiveData<Long>()
    val chronoDurationReady = MutableLiveData<Boolean>()

}
