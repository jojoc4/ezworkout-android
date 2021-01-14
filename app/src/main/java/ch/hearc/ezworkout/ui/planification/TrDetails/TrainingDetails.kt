package ch.hearc.ezworkout.ui.planification.TrDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.networking.MainViewModel
import ch.hearc.ezworkout.networking.MainViewModelFactory
import ch.hearc.ezworkout.networking.model.Training
import ch.hearc.ezworkout.networking.model.TrainingPlan
import ch.hearc.ezworkout.networking.repository.Repository
import ch.hearc.ezworkout.ui.planification.TPDetails.FragmentTraining
import ch.hearc.ezworkout.ui.planification.TPDetails.RenameDialog

class TrainingDetails : AppCompatActivity() {
    lateinit var Tr: Training

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_details)

        val Trid = intent.getIntExtra("ch.hearc.ezworkout.Trid", 120)
        getTr(Trid)

        val bundle = bundleOf("Trid" to Trid)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragment_container_view, FragmentExercise::class.java, bundle)
        }

        findViewById<Button>(R.id.rename).setOnClickListener { view ->
            val dialog = RenameDialog()
            dialog.name.value = Tr.name.toString()

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

    }

    private fun getTr(Trid: Int){
        val viewModel = ViewModelProvider(this,
            MainViewModelFactory(Repository(PreferenceManager.getDefaultSharedPreferences(this)))
        ).get(MainViewModel::class.java)
        viewModel.getTraining(Trid)
        viewModel.oneTrainingResponse.observe(this, Observer { response ->
            this.title = response.name
            this.Tr = response

        })
    }
}