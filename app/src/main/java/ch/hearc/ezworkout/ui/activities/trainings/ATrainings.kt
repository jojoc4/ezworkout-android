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


class ATrainings : Fragment() {

    companion object {
        fun newInstance() = ATrainings()
    }

    private lateinit var viewModel: ATrainingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // METHOD 1 TO PASS VALUES WITHIN THE SAME ACTIVITY
        val activity: MainActivity? = activity as MainActivity
        val mainMyString: String = activity?.getMyString() ?: "Error"
        Log.d("Var from activity", mainMyString)

        // METHOD 2 TO PASS VALUES TO ANOTHER ACTIVITY
        val root = inflater.inflate(R.layout.fragment_sync, container, false)
        val buttonStart: Button = root.findViewById(R.id.start)
        buttonStart.setOnClickListener {
            /* Create a new activity and pass the bundle to it
            val intent = Intent(activity, SecondActivity::class.java)
            val bundle = Bundle()
            bundle.putString("key_1", "VARIABLE FROM BUNDLE")
            intent.putExtras(bundle)
            startActivity(intent)
            */
            /* To recover the info in the target "SecondActivity" class
            Bundle bundle = getIntent().getExtras();
            String title = bundle.getString("key_1");
             */
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ATrainingsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}