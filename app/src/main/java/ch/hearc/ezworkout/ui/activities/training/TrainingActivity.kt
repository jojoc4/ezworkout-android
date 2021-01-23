package ch.hearc.ezworkout.ui.activities.training

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ch.hearc.ezworkout.R

class TrainingActivity : AppCompatActivity() {

    private val model: TrainingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val currentLBPid: Int = intent.getIntExtra("currentLBPid", 0)
        val trainingPlanId: Int = intent.getIntExtra("trainingPlanId", 0)
        val trainingId: Int = intent.getIntExtra("trainingId", 0)
        val trainingLabel: String? = intent.getStringExtra("trainingLabel")

        setContentView(R.layout.a_t_training_activity)
        title = "$trainingLabel"

        model.currentLBPid.value = currentLBPid
        model.trainingPlanId.value = trainingPlanId
        model.trainingId.value = trainingId
    }
}