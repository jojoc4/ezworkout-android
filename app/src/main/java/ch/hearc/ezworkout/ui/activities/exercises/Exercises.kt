package ch.hearc.ezworkout.ui.activities.exercises

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import ch.hearc.ezworkout.R

class Exercises : Fragment() {

    companion object {
        fun newInstance() = Exercises()
    }

    private lateinit var viewModel: ExercisesViewModel

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

        val root = inflater.inflate(R.layout.exercises_fragment, container, false)

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
            // TODO
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ExercisesViewModel::class.java)


        // TODO: Use the ViewModel
    }

}