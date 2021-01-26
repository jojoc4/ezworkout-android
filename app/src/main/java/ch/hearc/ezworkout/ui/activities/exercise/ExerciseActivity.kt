package ch.hearc.ezworkout.ui.activities.exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.ui.activities.training.TrainingViewModel

class ExerciseActivity : AppCompatActivity() {

    private val model: ExerciseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val trainingPlanId: Int = intent.getIntExtra("trainingPlanId", 0)
        val trainingId: Int = intent.getIntExtra("trainingId", 0)
        val trainingEffId: Int = intent.getIntExtra("trainingEffId", 0)
        val exerciseId: Int = intent.getIntExtra("exerciseId", 0)
        val exerciseLabel: String? = intent.getStringExtra("exerciseLabel")
        val serieCount: Int? = intent.getIntExtra("serieCount", 0)

        setContentView(R.layout.a_e_exercise_activity)
        title = "$exerciseLabel"

        model.trainingPlanId.value = trainingPlanId
        model.trainingId.value = trainingId
        model.trainingEffId.value = trainingEffId
        model.exerciseId.value = exerciseId
        model.serieCount.value = serieCount

        model.chronoDurationMilis.value = 10000
    }
}