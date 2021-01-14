package ch.hearc.ezworkout.ui.activities.training

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.ui.activities.exercise.ExerciseActivity
import ch.hearc.ezworkout.ui.settings.SettingsActivity

class TrainingFragment : Fragment() {

    companion object {
        fun newInstance() = TrainingFragment()
    }

    private lateinit var viewModel: TrainingViewModel

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
            // TODO PRINT
            Log.d(
                "viewModel : ", (viewModel != null).toString()
            )
            Log.d(
                "Value : ",
                (viewModel.selected.value != null).toString()
            )
            bundle.putString("exercise_id", viewModel.selected.value?.label)
            intent.putExtras(bundle)

            startActivity(intent)
        }

        val btnSettings: Button = root.findViewById(R.id.settings)
        btnSettings.setOnClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            startActivity(intent)
        }
        activity?.title = "Exercises"

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(TrainingViewModel::class.java)


        // TODO: Use the ViewModel
    }

}