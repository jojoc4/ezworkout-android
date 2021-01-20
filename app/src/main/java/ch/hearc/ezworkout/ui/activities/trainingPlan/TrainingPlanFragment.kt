package ch.hearc.ezworkout.ui.activities.trainingPlan

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
import ch.hearc.ezworkout.ui.activities.training.TrainingActivity
import ch.hearc.ezworkout.ui.settings.SettingsActivity

class TrainingPlanFragment : Fragment() {

    companion object {
        fun newInstance() = TrainingPlanFragment()
    }

    private lateinit var viewModel: TrainingPlanViewModel

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

        val root = inflater.inflate(R.layout.a_tp_training_plan_fragment, container, false)

        /*
        val trainingPlanFragment: TrainingPlanFragment = root.findViewById(R.id.training_plan_fragment)
        val trainingPlanFragmentManager: FragmentManager = trainingPlanFragment.childFragmentManager
        // We set the listener on the trainingPlan fragmentManager
        trainingPlanFragmentManager.setFragmentResultListener("requestKey") { key, bundle ->
            val result = bundle.getString("bundleKey")
            // Do something with the result..
        }
        */

        val btnStart: Button = root.findViewById(R.id.start)
        btnStart.setOnClickListener {
            // Create a new activity and pass the bundle to it
            val intent = Intent(activity, TrainingActivity::class.java)
            val bundle = Bundle()

            bundle.putInt("trainingId", viewModel.selected.value?.id!!)
            bundle.putString("trainingLabel", viewModel.selected.value?.label!!)
            intent.putExtras(bundle)

            startActivity(intent)
        }

        val btnSettings: Button = root.findViewById(R.id.settings)
        btnSettings.setOnClickListener{
            val intent = Intent(activity, SettingsActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(TrainingPlanViewModel::class.java)
        // requireActivity()


        // TODO: Use the ViewModel
    }

}