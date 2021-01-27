package ch.hearc.ezworkout.ui.activities.exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.networking.MainViewModel
import ch.hearc.ezworkout.networking.MainViewModelFactory
import ch.hearc.ezworkout.networking.model.TrainingEff
import ch.hearc.ezworkout.networking.repository.Repository
import ch.hearc.ezworkout.ui.activities.training.TrainingViewModel
import kotlinx.android.synthetic.main.a_e_exercise_history_fragment.*


class ExerciseActivity : AppCompatActivity() {

    private val model: ExerciseViewModel by viewModels()
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val trainingPlanId: Int = intent.getIntExtra("trainingPlanId", 0)
        val trainingId: Int = intent.getIntExtra("trainingId", 0)
        val trainingEffId: Int = intent.getIntExtra("trainingEffId", 0)
        val exerciseId: Int = intent.getIntExtra("exerciseId", 0)
        val exerciseLabel: String? = intent.getStringExtra("exerciseLabel")
        val serieCount: Int? = intent.getIntExtra("serieCount", 0)
        val currentLBPId: Int? = intent.getIntExtra("currentLBPId", 0)

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)

        setContentView(R.layout.a_e_exercise_activity)
        title = "$exerciseLabel"

        model.trainingPlanId.value = trainingPlanId
        model.trainingId.value = trainingId
        model.trainingEffId.value = trainingEffId
        model.exerciseId.value = exerciseId
        model.currentLBPId.value = currentLBPId
        
        model.serieCount.value = serieCount
        
        model.chronoDurationReady.value = false
        model.currentSerieIndex.value = 0

        // Access db data local model
        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(Repository(sharedPref))
        ).get(MainViewModel::class.java)

        mainViewModel.getExercise(exerciseId)

        model.chronoDurationMilis.value = 60000
        model.chronoEffDurationMilis.value = model.chronoDurationMilis.value
        model.isSerieDone.value = false;

        mainViewModel.oneExerciseResponse.observe(this, Observer { response ->
            model.chronoDurationMilis.value = response.pauseExercise.toLong() * 1000
            model.chronoDurationReady.value = true
        })

    }

}