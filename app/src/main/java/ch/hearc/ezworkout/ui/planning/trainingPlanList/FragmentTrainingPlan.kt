package ch.hearc.ezworkout.ui.planning.trainingPlanList

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.networking.MainViewModel
import ch.hearc.ezworkout.networking.MainViewModelFactory
import ch.hearc.ezworkout.networking.model.TrainingPlan
import ch.hearc.ezworkout.networking.repository.Repository
import ch.hearc.ezworkout.ui.planning.trainingPlanDetails.TrainingPlanDetails
import ch.hearc.ezworkout.ui.planning.utils.RenameDialog

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

        view.findViewById<Button>(R.id.add).setOnClickListener {
            val dialog = RenameDialog()
            dialog.name.value = ""

            dialog.show( parentFragmentManager, getString(R.string.add))
            dialog.name.observe(viewLifecycleOwner, {
                if(it != "") {
                    val viewModel = ViewModelProvider(
                        this,
                        MainViewModelFactory(
                            Repository(
                                PreferenceManager.getDefaultSharedPreferences(
                                    context
                                )
                            )
                        )
                    ).get(MainViewModel::class.java)
                    var TP = TrainingPlan()
                    TP.name = it
                    viewModel.addTrainingPlan(TP)
                    viewModel.newTrainingPlanResponse.observe(viewLifecycleOwner, { response ->
                        val intent = Intent(context, TrainingPlanDetails::class.java).apply {
                            putExtra("ch.hearc.ezworkout.TPid", response.id)
                        }
                        context?.let { it1 -> ContextCompat.startActivity(it1, intent, null) }
                    })
                }
            })
        }

        super.onViewCreated(view, savedInstanceState)
    }
}