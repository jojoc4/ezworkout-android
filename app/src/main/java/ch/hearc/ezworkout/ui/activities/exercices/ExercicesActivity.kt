package ch.hearc.ezworkout.ui.activities.exercices

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import ch.hearc.ezworkout.R

class ExercicesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // To recover the info in the target "SecondActivity" class
        val training_id: String? = intent.getStringExtra("training_id") // String?

        if (training_id == null)
            Log.d("SATLUT", "PAS DE TRAINING ID!!!")
        else
            Log.d("SATLUT", training_id)

        setContentView(R.layout.activity_exercices)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}