package ch.hearc.ezworkout.ui.activities.training

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.networking.MainViewModel
import ch.hearc.ezworkout.networking.MainViewModelFactory
import ch.hearc.ezworkout.networking.repository.Repository
import ch.hearc.ezworkout.ui.activities.trainingPlan.TrainingContent

/**
 * A fragment representing a list of exercises.
 */
class TrainingListFragment : Fragment() {

    private var columnCount = 1

    // Use the 'by activityViewModels()' Kotlin property delegate
    // from the fragment-ktx artifact
    private val model: TrainingViewModel by activityViewModels()
    private lateinit var myAdapter: TrainingRecyclerViewAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate( savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.a_t_training_list_fragment, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            myAdapter = TrainingRecyclerViewAdapter(ExerciseContent.ITEMS, model)

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(activity)

        // Access db data local model
        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(Repository(sharedPref))
        ).get(MainViewModel::class.java)

        val exerciseId = model.trainingId.value

        mainViewModel.LBPAndTrTrainingEffResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isEmpty()) {
                model.trainingEffId.value = null
            } else {
                model.trainingEffId.value = response.last().id
            }

            mainViewModel.getExercise(Integer(exerciseId!!))
        })

        // Exercise data handler
        mainViewModel.exerciseResponse.observe(viewLifecycleOwner, Observer { response ->
            // Add new data
            response.forEach {
                ExerciseContent.addItem(ExerciseContent.createExerciseItem(it.id, it.name!!, false, it.nbSerie))
            }

            // Select the first element by default
            model.selected.value = ExerciseContent.ITEMS[0]

            // Notify adapter
            myAdapter.notifyDataSetChanged()

            if (model.trainingEffId.value != null) {
                ExerciseContent.ITEMS.forEach {
                    mainViewModel.getExerciseEff(model.trainingEffId.value!!, it.id)
                }
            }
        })

        // ExerciseEff data handler (=> skipped)
        mainViewModel.ETrAndExExerciseEffResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isNotEmpty()) {
                ExerciseContent.ITEM_MAP[response.first().exerciseId]!!.skipped = response.first().skipped == 1
                myAdapter.notifyDataSetChanged()
            }
        })

        getData()
    }

    private fun getData() {
        // Clear local data
        ExerciseContent.ITEMS.clear()
        ExerciseContent.ITEM_MAP.clear()
        model.selected.value = null

        // Get current LBPageId
        mainViewModel.getTrainingEff(model.currentLBPid.value!!, model.trainingId.value!!)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            TrainingListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}