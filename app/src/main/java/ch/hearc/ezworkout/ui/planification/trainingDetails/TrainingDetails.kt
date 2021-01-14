package ch.hearc.ezworkout.ui.planification.trainingDetails

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.networking.MainViewModel
import ch.hearc.ezworkout.networking.MainViewModelFactory
import ch.hearc.ezworkout.networking.model.Training
import ch.hearc.ezworkout.networking.repository.Repository
import ch.hearc.ezworkout.ui.planification.utils.RenameDialog

class TrainingDetails : AppCompatActivity() {
    lateinit var training: Training

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_details)

        val Trid = intent.getIntExtra("ch.hearc.ezworkout.Trid", 120)
        val TPid = intent.getIntExtra("ch.hearc.ezworkout.TPid", 120)
        getTr(Trid)

        val bundle = bundleOf("Trid" to Trid)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragment_container_view, FragmentExercise::class.java, bundle)
        }

        findViewById<Button>(R.id.rename).setOnClickListener {
            val dialog = RenameDialog()
            dialog.name.value = training.name.toString()

            dialog.show(supportFragmentManager, "Renommer")
            dialog.name.observe(this, {
                val viewModel = ViewModelProvider(this,
                    MainViewModelFactory(Repository(PreferenceManager.getDefaultSharedPreferences(this)))
                ).get(MainViewModel::class.java)
                var updatedTr= Training()
                updatedTr.id = Trid
                updatedTr.name = it
                viewModel.updateTraining(updatedTr)
                this.title =it
            })
        }

        findViewById<Button>(R.id.delete).setOnClickListener {
            var db = AlertDialog.Builder(this)
            db.setPositiveButton("Supprimer", DialogInterface.OnClickListener { _, _ ->
                val viewModel = ViewModelProvider(this,
                    MainViewModelFactory(Repository(PreferenceManager.getDefaultSharedPreferences(this)))
                ).get(MainViewModel::class.java)
                viewModel.delTraining(training, TPid)
                finish()
            })
            db.setNegativeButton("Annuler", DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
            })
            db.setMessage("Êtes-vous sûr de voiloir supprimer cet entraînement?")
            var ad = db.create()
            ad.show()
        }

    }

    private fun getTr(Trid: Int){
        val viewModel = ViewModelProvider(this,
            MainViewModelFactory(Repository(PreferenceManager.getDefaultSharedPreferences(this)))
        ).get(MainViewModel::class.java)
        viewModel.getTraining(Trid)
        viewModel.oneTrainingResponse.observe(this, Observer { response ->
            this.title = response.name
            this.training = response

        })
    }
}