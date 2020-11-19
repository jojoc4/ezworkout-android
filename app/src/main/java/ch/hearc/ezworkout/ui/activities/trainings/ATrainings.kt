package ch.hearc.ezworkout.ui.activities.trainings

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import ch.hearc.ezworkout.MainActivity
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.ui.activities.exercices.ExercicesActivity
import ch.hearc.ezworkout.ui.activities.training.TrainingContent
import java.nio.channels.Selector

class ATrainings : Fragment() {

    companion object {
        fun newInstance() = ATrainings()
    }

    private lateinit var viewModel: ATrainingsViewModel

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

        val root = inflater.inflate(R.layout.a_trainings_fragment, container, false)

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
            val intent = Intent(activity, ExercicesActivity::class.java)
            val bundle = Bundle()
            // TODO PRINT
            //bundle.putString("training_id", viewModel.selected.value?.label)
            //intent.putExtras(bundle)

            //startActivity(intent)
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ATrainingsViewModel::class.java)


        // TODO: Use the ViewModel
    }

}