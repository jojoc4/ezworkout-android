package ch.hearc.ezworkout.ui.planning.exerciseDetails

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.networking.MainViewModel
import ch.hearc.ezworkout.networking.MainViewModelFactory
import ch.hearc.ezworkout.networking.model.Exercise
import ch.hearc.ezworkout.networking.repository.Repository
import ch.hearc.ezworkout.ui.planning.utils.RenameDialog


class ExerciseDetails : AppCompatActivity() {
    lateinit var exercise: Exercise

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_details)

        val exerciseId = intent.getIntExtra("ch.hearc.ezworkout.exId", 120)
        val trid = intent.getIntExtra("ch.hearc.ezworkout.trid", 120)
        getExercise(exerciseId)

        val viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(Repository(PreferenceManager.getDefaultSharedPreferences(this)))
        ).get(MainViewModel::class.java)

        findViewById<Button>(R.id.rename).setOnClickListener {
            val dialog = RenameDialog()
            dialog.name.value = exercise.name.toString()
            dialog.show(supportFragmentManager, getString(R.string.rename))
            dialog.name.observe(this, {
                exercise.name = it
                viewModel.updateExercise(exercise)
                this.title = it
            })
        }

        findViewById<Button>(R.id.delete).setOnClickListener {
            var db = AlertDialog.Builder(this)
            db.setPositiveButton(getString(R.string.delete), DialogInterface.OnClickListener { _, _ ->
                val viewModel = ViewModelProvider(this,
                    MainViewModelFactory(Repository(PreferenceManager.getDefaultSharedPreferences(this)))
                ).get(MainViewModel::class.java)
                viewModel.delExercise(exercise, trid)
                finish()
            })
            db.setNegativeButton(getString(R.string.cancel), DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
            })
            db.setMessage(getString(R.string.exercise_delete_confirmation))
            var ad = db.create()
            ad.show()
        }


        findViewById<Button>(R.id.comment_s).setOnClickListener {
            exercise.comment = findViewById<EditText>(R.id.comment).text.toString()
            viewModel.updateExercise(exercise)
        }

        findViewById<Button>(R.id.nbSeries_m).setOnClickListener {
            exercise.nbSerie--
            findViewById<TextView>(R.id.nbSerie).text = exercise.nbSerie.toString()
            viewModel.updateExercise(exercise)
            if(exercise.nbSerie==1){
                it.isEnabled = false
            }
        }

        findViewById<Button>(R.id.nbSeries_p).setOnClickListener {
            exercise.nbSerie++
            findViewById<TextView>(R.id.nbSerie).text = exercise.nbSerie.toString()
            viewModel.updateExercise(exercise)
            findViewById<Button>(R.id.nbSeries_m).isEnabled = true
        }

        findViewById<Button>(R.id.repMin_m).setOnClickListener {
            exercise.repMin--
            findViewById<TextView>(R.id.repMin).text = exercise.repMin.toString()
            viewModel.updateExercise(exercise)
            if(exercise.repMin==1){
                it.isEnabled = false
            }
        }

        findViewById<Button>(R.id.repMin_p).setOnClickListener {
            exercise.repMin++
            findViewById<TextView>(R.id.repMin).text = exercise.repMin.toString()
            viewModel.updateExercise(exercise)
            findViewById<Button>(R.id.repMin_m).isEnabled = true
        }

        findViewById<Button>(R.id.repMax_m).setOnClickListener {
            exercise.repMax--
            findViewById<TextView>(R.id.repMax).text = exercise.repMax.toString()
            viewModel.updateExercise(exercise)
            if(exercise.repMax==1){
                it.isEnabled = false
            }
        }

        findViewById<Button>(R.id.repMax_p).setOnClickListener {
            exercise.repMax++
            findViewById<TextView>(R.id.repMax).text = exercise.repMax.toString()
            viewModel.updateExercise(exercise)
            findViewById<Button>(R.id.repMax_m).isEnabled = true
        }

        findViewById<Button>(R.id.pauseSerie_m).setOnClickListener {
            exercise.pauseSerie--
            findViewById<TextView>(R.id.pauseSerie).text = exercise.pauseSerie.toString()
            viewModel.updateExercise(exercise)
            if(exercise.pauseSerie==0){
                it.isEnabled = false
            }
        }

        findViewById<Button>(R.id.pauseSerie_p).setOnClickListener {
            exercise.pauseSerie++
            findViewById<TextView>(R.id.pauseSerie).text = exercise.pauseSerie.toString()
            viewModel.updateExercise(exercise)
            findViewById<Button>(R.id.pauseSerie_m).isEnabled = true
        }

        findViewById<Button>(R.id.pauseExercise_m).setOnClickListener {
            exercise.pauseExercise--
            findViewById<TextView>(R.id.pauseExercise).text = exercise.pauseExercise.toString()
            viewModel.updateExercise(exercise)
            if(exercise.pauseExercise==0){
                it.isEnabled = false
            }
        }

        findViewById<Button>(R.id.pauseExercise_p).setOnClickListener {
            exercise.pauseExercise++
            findViewById<TextView>(R.id.pauseExercise).text = exercise.pauseExercise.toString()
            viewModel.updateExercise(exercise)
            findViewById<Button>(R.id.pauseExercise_m).isEnabled = true
        }

    }

    private fun getExercise(exerciseId: Int){
        val viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(Repository(PreferenceManager.getDefaultSharedPreferences(this)))
        ).get(MainViewModel::class.java)
        viewModel.getExercise(exerciseId)
        viewModel.oneExerciseResponse.observe(this, Observer { response ->
            this.title = response.name
            this.exercise = response
            findViewById<EditText>(R.id.comment).setText(exercise.comment)
            findViewById<TextView>(R.id.nbSerie).text = exercise.nbSerie.toString()
            findViewById<TextView>(R.id.repMin).text = exercise.repMin.toString()
            findViewById<TextView>(R.id.repMax).text = exercise.repMax.toString()
            findViewById<TextView>(R.id.pauseSerie).text = exercise.pauseSerie.toString()
            findViewById<TextView>(R.id.pauseExercise).text = exercise.pauseExercise.toString()

            if (exercise.nbSerie == 1) {
                findViewById<Button>(R.id.nbSeries_m).isEnabled = false
            }
            if (exercise.repMin == 1) {
                findViewById<Button>(R.id.repMin_m).isEnabled = false
            }
            if (exercise.repMax == 1) {
                findViewById<Button>(R.id.repMax_m).isEnabled = false
            }
            if (exercise.pauseSerie == 0) {
                findViewById<Button>(R.id.pauseSerie_m).isEnabled = false
            }
            if (exercise.pauseExercise == 0) {
                findViewById<Button>(R.id.pauseExercise_m).isEnabled = false
            }
        })
    }
}