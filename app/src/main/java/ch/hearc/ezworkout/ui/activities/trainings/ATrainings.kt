package ch.hearc.ezworkout.ui.activities.trainings

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import ch.hearc.ezworkout.MainActivity
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.ui.activities.exercices.ExercicesActivity


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
        val buttonStart: Button = root.findViewById(R.id.start)
        buttonStart.setOnClickListener {
            // Create a new activity and pass the bundle to it
            val intent = Intent(activity, ExercicesActivity::class.java)
            val bundle = Bundle()
            bundle.putString("training_id", "1")
            intent.putExtras(bundle)
            startActivity(intent)
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ATrainingsViewModel::class.java)


        // TODO: Use the ViewModel
    }

}