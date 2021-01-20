package ch.hearc.ezworkout.ui.activities.training

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProviders
import ch.hearc.ezworkout.R

class TrainingActivity : AppCompatActivity() {

    private val model: TrainingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val trainingId: Int = intent.getIntExtra("trainingId", 0)
        val trainingLabel: String? = intent.getStringExtra("trainingLabel")

        setContentView(R.layout.a_t_training_activity)
        title = "$trainingId : $trainingLabel"

        model.exerciseId.value = trainingId
    }
}