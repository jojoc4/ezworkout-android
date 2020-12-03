package ch.hearc.ezworkout.ui.activities.exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ch.hearc.ezworkout.R

class ExerciseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val exercise_id: String? = intent.getStringExtra("exercise_id") // String?

        if (exercise_id == null)
            Log.d("SALUT", "PAS DE exercise_id!!")
        else
            Log.d("SALUT", exercise_id)

        setContentView(R.layout.a_e_exercise_activity)
        setTitle(exercise_id)
    }
}