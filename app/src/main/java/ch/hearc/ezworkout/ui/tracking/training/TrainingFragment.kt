package ch.hearc.ezworkout.ui.tracking.training

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
import ch.hearc.ezworkout.networking.model.ExerciseEff
import ch.hearc.ezworkout.networking.repository.Repository
import ch.hearc.ezworkout.ui.tracking.exercise.ExerciseActivity

class TrainingFragment : Fragment() {

    companion object {
        fun newInstance() = TrainingFragment()
    }

    private val model: TrainingViewModel by activityViewModels()
    private lateinit var mainViewModel: MainViewModel
    private var skipping: Boolean = false
    private var checkingExerciseEff: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.a_t_training_fragment, container, false)

        // Get mainViewModel
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(activity)
        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(Repository(sharedPref))
        ).get(MainViewModel::class.java)

        // Observer
        mainViewModel.ETrAndExExerciseEffResponse.observe(viewLifecycleOwner, Observer { response ->
            if (skipping) {
                if (response.isNotEmpty()) {
                    response.first().skipped = 1

                    mainViewModel.updateExerciseEff(response.first())
                } else {
                    val exerciseEff = ExerciseEff()
                    exerciseEff.trainingEffId = model.trainingEffId.value!!
                    exerciseEff.exerciseId = model.selected.value!!.id
                    exerciseEff.pause = 0
                    exerciseEff.rating = 1
                    exerciseEff.skipped = 1

                    mainViewModel.addExerciseEff(exerciseEff)
                }
            } else {
                if (response.isNotEmpty()) {
                    response.first().skipped = 0

                    mainViewModel.updateExerciseEff(response.first())
                } else {
                    val exerciseEff = ExerciseEff()
                    exerciseEff.trainingEffId = model.trainingEffId.value!!
                    exerciseEff.exerciseId = model.selected.value!!.id
                    exerciseEff.pause = 0
                    exerciseEff.rating = 1
                    exerciseEff.skipped = 0

                    mainViewModel.addExerciseEff(exerciseEff)
                }

                // Create a new activity and pass the bundle to it
                val intent = Intent(activity, ExerciseActivity::class.java)
                val bundle = Bundle()

                bundle.putInt("exerciseId", model.selected.value?.id!!)
                bundle.putString("exerciseLabel", model.selected.value?.label!!)
                bundle.putInt("trainingId", model.trainingId.value!!)
                bundle.putInt("trainingEffId", model.trainingEffId.value!!)
                bundle.putInt("trainingPlanId", model.trainingPlanId.value!!)
                bundle.putInt("serieCount", model.selected.value!!.serieCount)
                bundle.putInt("currentLBPId", model.currentLBPid.value!!)
                intent.putExtras(bundle)

                startActivity(intent)
            }

            checkingExerciseEff = false
        })

        // Start button handler
        val btnStart: Button = root.findViewById(R.id.start)
        btnStart.setOnClickListener {
            if (!checkingExerciseEff && model.currentLBPid.value != null) {
                checkingExerciseEff = true
                skipping = false
                model.selected.value!!.skipped = false

                mainViewModel.getExerciseEff(model.trainingEffId.value!!, model.selected.value!!.id)
            } else Log.d("Err", "Already busy!")
        }

        // Skip button handler
        val btnSkip: Button = root.findViewById(R.id.skip)
        btnSkip.setOnClickListener {
            if (!checkingExerciseEff && model.currentLBPid.value != null) {
                checkingExerciseEff = true
                skipping = true
                model.selected.value!!.skipped = true

                mainViewModel.getExerciseEff(model.trainingEffId.value!!, model.selected.value!!.id)
            } else Log.d("Err", "Already busy!")
        }

        return root
    }
}