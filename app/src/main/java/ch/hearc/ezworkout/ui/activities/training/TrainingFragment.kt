package ch.hearc.ezworkout.ui.activities.training

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProviders
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.ui.activities.exercise.ExerciseActivity
import ch.hearc.ezworkout.ui.settings.SettingsActivity

class TrainingFragment : Fragment() {

    companion object {
        fun newInstance() = TrainingFragment()
    }

    private val viewModel: TrainingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // METHOD TO PASS VALUES WITHIN THE SAME ACTIVITY
        /*
        val activity: MainActivity? = activity as MainActivity
        val mainMyString: String = activity?.getMyString() ?: "Error"
        Log.d("Var from activity", mainMyString)
        */

        val root = inflater.inflate(R.layout.a_t_training_fragment, container, false)

        /*
        val trainingPlanFragment: TrainingPlanFragment = root.findViewById(R.id.training_plan_fragment)
        val trainingPlanFragmentManager: FragmentManager = trainingPlanFragment.childFragmentManager
        // We set the listener on the trainingPlan fragmentManager
        trainingPlanFragmentManager.setFragmentResultListener("requestKey") { key, bundle ->
            val result = bundle.getString("bundleKey")
            // Do something with the result..
        }
        */

        val buttonStart: Button = root.findViewById(R.id.start)
        buttonStart.setOnClickListener {
            // Create a new activity and pass the bundle to it
            val intent = Intent(activity, ExerciseActivity::class.java)
            val bundle = Bundle()

            bundle.putInt("exerciseId", viewModel.selected.value?.id!!)
            bundle.putString("exerciseLabel", viewModel.selected.value?.label!!)
            bundle.putInt("trainingId", viewModel.trainingId.value!!)
            bundle.putInt("trainingPlanId", viewModel.trainingPlanId.value!!)
            intent.putExtras(bundle)

            startActivity(intent)
        }

        val btnSettings: Button = root.findViewById(R.id.settings)
        btnSettings.setOnClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }

}