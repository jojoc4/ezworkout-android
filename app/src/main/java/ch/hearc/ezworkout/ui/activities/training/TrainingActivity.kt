package ch.hearc.ezworkout.ui.activities.training

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ch.hearc.ezworkout.R

class TrainingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val training_id: String? = intent.getStringExtra("training_id") // String?

        if (training_id == null)
            Log.d("SALUT", "PAS DE TRAINING ID!!!")
        else
            Log.d("SALUT", training_id)

        setContentView(R.layout.a_t_training_activity)
        setTitle("Training")
    }
}