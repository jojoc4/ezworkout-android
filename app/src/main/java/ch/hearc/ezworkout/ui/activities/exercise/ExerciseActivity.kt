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

        val exerciseId: Int = intent.getIntExtra("exerciseId", 0)
        val exerciseLabel: String? = intent.getStringExtra("exerciseLabel")

        setContentView(R.layout.a_e_exercise_activity)
        title = "$exerciseId : $exerciseLabel"

        model.exerciseId.value = exerciseId
    }
}