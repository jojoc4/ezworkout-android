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
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.networking.MainViewModel
import ch.hearc.ezworkout.networking.MainViewModelFactory
import ch.hearc.ezworkout.networking.repository.Repository
import ch.hearc.ezworkout.ui.activities.training.TrainingActivity
import ch.hearc.ezworkout.ui.settings.SettingsActivity

class TrainingPlanFragment : Fragment() {

    companion object {
        fun newInstance() = TrainingPlanFragment()
    }

    private val model: TrainingPlanViewModel by activityViewModels()
    private lateinit var mainViewModel: MainViewModel
    private var checkingTrainingEff: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.a_tp_training_plan_fragment, container, false)

        // Get mainViewModel and currentTPid
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(activity)
        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(Repository(sharedPref))
        ).get(MainViewModel::class.java)
        val currentTPid = sharedPref.getInt("currentTPid", 1)

        // Start button handler
        val btnStart: Button = root.findViewById(R.id.start)
        btnStart.setOnClickListener {
            if (!checkingTrainingEff) {
                checkingTrainingEff = true

                //mainViewModel.getTrainingEff(currentTPid, model.selected.value!!.id)
                mainViewModel.oneTrainingEffResponse.observe(viewLifecycleOwner, Observer { response ->
                    if (response != null) {

                    }

                    // Create a new activity and pass the bundle to it
                    val intent = Intent(activity, TrainingActivity::class.java)
                    val bundle = Bundle()

                    bundle.putInt("trainingId", model.selected.value!!.id)
                    bundle.putString("trainingLabel", model.selected.value!!.label)
                    bundle.putInt("trainingPlanId", model.trainingPlanId.value!!)
                    intent.putExtras(bundle)

                    startActivity(intent)
                })
            } else Log.d("Err", "Already busy!")
        }

        // Skip button handler
        val btnSkip: Button = root.findViewById(R.id.skip)
        btnSkip.setOnClickListener {
            if (!checkingTrainingEff) {
                checkingTrainingEff = true

                //mainViewModel.getTrainingEff(currentTPid, model.selected.value!!.id)
                mainViewModel.trainingEffResponse.observe(viewLifecycleOwner, Observer { response ->
                    // TODO
                })
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