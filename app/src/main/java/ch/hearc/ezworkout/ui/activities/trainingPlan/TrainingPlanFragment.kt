package ch.hearc.ezworkout.ui.activities.trainingPlan

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.networking.MainViewModel
import ch.hearc.ezworkout.networking.MainViewModelFactory
import ch.hearc.ezworkout.networking.model.TrainingEff
import ch.hearc.ezworkout.networking.repository.Repository
import ch.hearc.ezworkout.ui.activities.training.TrainingActivity
import ch.hearc.ezworkout.ui.settings.SettingsActivity
import java.time.LocalDateTime

class TrainingPlanFragment : Fragment() {

    companion object {
        fun newInstance() = TrainingPlanFragment()
    }

    private val model: TrainingPlanViewModel by activityViewModels()
    private lateinit var mainViewModel: MainViewModel
    private var skipping: Boolean = false
    private var checkingTrainingEff: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.a_tp_training_plan_fragment, container, false)

        // Get mainViewModel
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(activity)
        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(Repository(sharedPref))
        ).get(MainViewModel::class.java)

        // Observer
        mainViewModel.LBPAndTrTrainingEffResponse.observe(viewLifecycleOwner, Observer { response ->
            if (skipping) {
                if (response.isNotEmpty()) {
                    response.first().skipped = 1

                    mainViewModel.updateTrainingEff(response.first())
                } else {
                    val trainingEff = TrainingEff()
                    trainingEff.logbookPageId = model.currentLBPid.value!!
                    trainingEff.trainingId = model.selected.value!!.id
                    trainingEff.date = LocalDateTime.now().toString()
                    trainingEff.skipped = 1

                    mainViewModel.addTrainingEff(trainingEff)
                }
            } else {
                if (response.isNotEmpty()) {
                    response.first().skipped = 0

                    mainViewModel.updateTrainingEff(response.first())
                } else {
                    val trainingEff = TrainingEff()
                    trainingEff.logbookPageId = model.currentLBPid.value!!
                    trainingEff.trainingId = model.selected.value!!.id
                    trainingEff.date = LocalDateTime.now().toString()
                    trainingEff.skipped = 0

                    mainViewModel.addTrainingEff(trainingEff)
                }

                // Create a new activity and pass the bundle to it
                val intent = Intent(activity, TrainingActivity::class.java)
                val bundle = Bundle()

                bundle.putInt("currentLBPid", model.currentLBPid.value!!)
                bundle.putInt("trainingId", model.selected.value!!.id)
                bundle.putString("trainingLabel", model.selected.value!!.label)
                bundle.putInt("trainingPlanId", model.trainingPlanId.value!!)
                intent.putExtras(bundle)

                startActivity(intent)
            }

            checkingTrainingEff = false
        })

        // Start button handler
        val btnStart: Button = root.findViewById(R.id.start)
        btnStart.setOnClickListener {
            if (!checkingTrainingEff && model.currentLBPid.value != null) {
                checkingTrainingEff = true
                skipping = false
                model.selected.value!!.skipped = false

                mainViewModel.getTrainingEff(model.currentLBPid.value!!, model.selected.value!!.id)
            } else Log.d("Err", "Already busy!")
        }

        // Skip button handler
        val btnSkip: Button = root.findViewById(R.id.skip)
        btnSkip.setOnClickListener {
            if (!checkingTrainingEff && model.currentLBPid.value != null) {
                checkingTrainingEff = true
                skipping = true
                model.selected.value!!.skipped = true

                mainViewModel.getTrainingEff(model.currentLBPid.value!!, model.selected.value!!.id)
            } else Log.d("Err", "Already busy!")
        }

        // Settings button handler
        val btnSettings: Button = root.findViewById(R.id.settings)
        btnSettings.setOnClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            startActivity(intent)
        }

        return root
    }

}