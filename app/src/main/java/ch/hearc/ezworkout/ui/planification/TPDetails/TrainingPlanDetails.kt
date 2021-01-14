package ch.hearc.ezworkout.ui.planification.TPDetails

import android.os.Bundle
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.networking.MainViewModel
import ch.hearc.ezworkout.networking.MainViewModelFactory
import ch.hearc.ezworkout.networking.model.TrainingPlan
import ch.hearc.ezworkout.networking.repository.Repository

class TrainingPlanDetails : AppCompatActivity() {
    lateinit var TP: TrainingPlan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tp_details)
        setSupportActionBar(findViewById(R.id.toolbar))

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        val TPid = intent.getIntExtra("ch.hearc.ezworkout.TPid", 120)

        getTP(TPid)

        val bundle = bundleOf("TPid" to TPid)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragment_container_view, FragmentTraining::class.java, bundle)
        }

        findViewById<Button>(R.id.rename).setOnClickListener { view ->
            val dialog = RenameDialog()
            dialog.name.value = TP.name.toString()

            dialog.show(supportFragmentManager, "Renommer")
            dialog.name.observe(this, {
                val viewModel = ViewModelProvider(this,
                    MainViewModelFactory(Repository(PreferenceManager.getDefaultSharedPreferences(this)))
                ).get(MainViewModel::class.java)
                var updatedTP= TrainingPlan()
                updatedTP.id = TPid
                updatedTP.name = it
                viewModel.updateTrainingPlan(updatedTP)
                this.title =it
            })
        }

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Sélectionné comme plan actuel", Snackbar.LENGTH_LONG).show()
            with (sharedPref?.edit()) {
                this?.putInt("currentTPid", TPid)
                this?.apply()
            }

        }
    }

    private fun getTP(TPid: Int){
        val viewModel = ViewModelProvider(this,
            MainViewModelFactory(Repository(PreferenceManager.getDefaultSharedPreferences(this)))
        ).get(MainViewModel::class.java)
        viewModel.getTrainingPlan(TPid)
        viewModel.oneTrainingPlanResponse.observe(this, Observer { response ->
            this.title = response.name
            this.TP = response

        })
    }
}