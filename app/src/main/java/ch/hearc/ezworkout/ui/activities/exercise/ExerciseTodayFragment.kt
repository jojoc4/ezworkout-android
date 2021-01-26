package ch.hearc.ezworkout.ui.activities.exercise

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.networking.MainViewModel
import ch.hearc.ezworkout.networking.MainViewModelFactory
import ch.hearc.ezworkout.networking.repository.Repository

/**
 * A simple [Fragment] subclass.
 * Use the [ExerciseTodayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExerciseTodayFragment : Fragment() {

    private val model: ExerciseViewModel by activityViewModels()
    lateinit var myAdapter: ExerciseRecyclerViewAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.a_e_exercise_today_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(activity)

        // Access db data local model
        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(Repository(sharedPref))
        ).get(MainViewModel::class.java)

        myAdapter = ExerciseRecyclerViewAdapter(SerieContent.ITEMS, model)

        mainViewModel.getExerciseEff(Integer(model.trainingEffId.value!!))

        mainViewModel.exerciseEffResponse.observe(viewLifecycleOwner, { response ->
            if (response.isNotEmpty()) {

                response.forEach {
                    if (it.exerciseId == model.exerciseId.value) {
                        model.exerciseEffId.value = it.id
                        Log.d("Bro", "exerciseEffId")
                        Log.d("Bro", model.exerciseEffId.value.toString())
                    }
                }

                if (model.exerciseEffId.value != null) {

                    mainViewModel.getSeriesEff(Integer(model.exerciseEffId.value!!))

                    mainViewModel.seriesEffResponse.observe(viewLifecycleOwner, { response ->

                        Log.d("Bro", "count")
                        Log.d("Bro", response.count().toString())
                        model.serieCountEff.value = response.count()
                    })

                }
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ExerciseTodayFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            ExerciseTodayFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}