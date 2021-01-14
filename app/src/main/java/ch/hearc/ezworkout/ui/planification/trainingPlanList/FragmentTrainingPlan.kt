package ch.hearc.ezworkout.ui.planification.trainingPlanList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.ui.planification.trainingPlanDetails.FragmentTraining

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentTrainingPlan.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentTrainingPlan : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_training_plan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var childFragment = FragmentTrainingPlanList()
        var transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.child_fragment_container, childFragment).commit()
        super.onViewCreated(view, savedInstanceState)
    }
}