package ch.hearc.ezworkout.ui.activities.exercise

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.networking.MainViewModel
import ch.hearc.ezworkout.networking.MainViewModelFactory
import ch.hearc.ezworkout.networking.model.ExerciseEff
import ch.hearc.ezworkout.networking.model.TrainingEff
import ch.hearc.ezworkout.networking.repository.Repository
import ch.hearc.ezworkout.ui.activities.training.ExerciseContent

/**
 * A simple [Fragment] subclass.
 * Use the [ExerciseHistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExerciseHistoryFragment : Fragment() {

    private val model: ExerciseViewModel by activityViewModels()
    private lateinit var myAdapter: ArrayAdapter<String>
    private lateinit var mainViewModel: MainViewModel
    private lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(
            ch.hearc.ezworkout.R.layout.a_e_exercise_history_fragment,
            container,
            false
        )

        // Get ListView object from xml
        listView = root.findViewById(R.id.list) as ListView

        // Defined Array values to show in ListView
        val values = mutableListOf(
            "No data found"
        )

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        myAdapter = ArrayAdapter<String>(
            requireActivity().applicationContext,
            android.R.layout.simple_list_item_1, android.R.id.text1, values
        )

        // Assign adapter to ListView
        listView!!.adapter = myAdapter

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(activity)

        // Access db data local model
        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(Repository(sharedPref))
        ).get(MainViewModel::class.java)

        val trainingPlanId = model.trainingPlanId.value
        val trainingId = model.trainingId.value
        val exerciseId = model.exerciseId.value

        // Load data
        mainViewModel.getLogbookPage(Integer(trainingPlanId!!))
        mainViewModel.logbookPageResponse.observe(viewLifecycleOwner, Observer { response ->
            val logbookPages = response
            val lastLogbookPage = if (logbookPages.isEmpty()) null else logbookPages.last()

            if (lastLogbookPage != null) {
                mainViewModel.getTrainingEff(Integer(lastLogbookPage.id))
                mainViewModel.trainingEffResponse.observe(viewLifecycleOwner, Observer { response ->
                    val effTrainings = response
                    var effTraining: TrainingEff? = null

                    effTrainings.forEach {
                        if (it.trainingId == trainingId) effTraining = it
                    }

                    if (effTraining != null) {
                        mainViewModel.getExerciseEff(Integer(effTraining!!.id))
                        mainViewModel.exerciseEffResponse.observe(
                            viewLifecycleOwner,
                            Observer { response ->
                                val effExercises = response
                                var effExercise: ExerciseEff? = null

                                effExercises.forEach {
                                    if (it.exerciseId == exerciseId) effExercise = it
                                }

                                if (effExercise != null) {
                                    mainViewModel.getSeriesEff(Integer(effExercise!!.id))
                                    mainViewModel.seriesEffResponse.observe(
                                        viewLifecycleOwner,
                                        Observer { response ->
                                            val effSeries = response

                                            myAdapter.clear()

                                            var i = 1
                                            effSeries.forEach {
                                                myAdapter.add("Série " + i + " : " + it.weight + "kg - x" + it.rep)
                                                i++
                                            }

                                            // Notify adapter
                                            myAdapter.notifyDataSetChanged()
                                        })
                                } else Log.d("Err", "No effExercise found")
                            })
                    } else Log.d("Err", "No effTraining found")
                })
            } else Log.d("Err", "No lastbookpage found")
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ExerciseHistoryFragment.
         */
        @JvmStatic
        fun newInstance() = ExerciseHistoryFragment().apply { arguments = Bundle().apply { } }
    }
}