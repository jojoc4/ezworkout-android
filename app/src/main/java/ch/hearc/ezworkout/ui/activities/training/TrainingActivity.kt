package ch.hearc.ezworkout.ui.activities.training

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import ch.hearc.ezworkout.R

class TrainingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val trainingId: Int = intent.getIntExtra("trainingId", 0)

        setContentView(R.layout.a_t_training_activity)
        setTitle(trainingId.toString())

        val viewModel = ViewModelProviders.of(this).get(TrainingViewModel::class.java)
        viewModel.exerciseId.value = trainingId
    }
}