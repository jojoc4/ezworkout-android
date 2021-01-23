package ch.hearc.ezworkout.ui.activities.trainingPlan

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.networking.MainViewModel
import ch.hearc.ezworkout.networking.MainViewModelFactory
import ch.hearc.ezworkout.networking.model.TrainingEff
import ch.hearc.ezworkout.networking.repository.Repository
import ch.hearc.ezworkout.ui.activities.training.TrainingActivity
import java.time.LocalDateTime

/**
 * A fragment representing a list of trainings.
 */
class TrainingPlanListFragment : Fragment() {

    private var columnCount = 1

    // Use the 'by activityViewModels()' Kotlin property delegate
    // from the fragment-ktx artifact
    private val model: TrainingPlanViewModel by activityViewModels()
    private lateinit var myAdapter: TrainingPlanRecyclerViewAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.a_tp_training_plan_list_fragment, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            myAdapter = TrainingPlanRecyclerViewAdapter(TrainingContent.ITEMS, model)

            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = myAdapter
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(activity)

        // Clear local data
        TrainingContent.ITEMS.clear()
        TrainingContent.ITEM_MAP.clear()
        model.selected.value = null

        // Access db data local model
        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(Repository(sharedPref))
        ).get(MainViewModel::class.java)

        // TODO: if no currentTPid found => ask user to choose one

        val currentTPid = sharedPref.getInt("currentTPid", 1)
        model.trainingPlanId.value = currentTPid

        // Get current LBPageId
        mainViewModel.getLogbookPage(Integer(currentTPid))
        mainViewModel.logbookPageResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isEmpty()) {
                model.currentLBPid.value = null
            } else {
                model.currentLBPid.value = response.last().id
            }

            mainViewModel.getTraining(Integer(currentTPid))
        })

        // Training data handler
        mainViewModel.trainingResponse.observe(viewLifecycleOwner, Observer { response ->
            // Add new data
            response.forEach {
                TrainingContent.addItem(TrainingContent.createTrainingItem(it.id, it.name!!, false))
            }

            // Select the first element by default
            model.selected.value = TrainingContent.ITEMS[0]

            // Notify adapter
            myAdapter.notifyDataSetChanged()

            if (model.currentLBPid.value != null) {
                TrainingContent.ITEMS.forEach {
                    mainViewModel.getTrainingEff(model.currentLBPid.value!!, it.id)
                }
            }
        })

        // TrainingEff data handler (=> skipped)
        mainViewModel.LBPAndTrTrainingEffResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isNotEmpty()) {
                TrainingContent.ITEM_MAP[response.first().trainingId]!!.skipped = response.first().skipped == 1
                myAdapter.notifyDataSetChanged()
            }
        })
    }


    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            TrainingPlanListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}