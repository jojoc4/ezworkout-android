package ch.hearc.ezworkout.ui.activities.exercise

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
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
import kotlinx.android.synthetic.main.a_e_exercise_history_fragment.*
import java.lang.Exception

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
    private var loadingData: Boolean = true

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

        // ListView
        listView = root.findViewById(R.id.list) as ListView
        val values = mutableListOf("")
        myAdapter = ArrayAdapter<String>(
            requireActivity().applicationContext,
            android.R.layout.simple_list_item_1, android.R.id.text1, values
        )
        listView!!.adapter = myAdapter

        // Prev button
        val btnPrev: Button = root.findViewById(R.id.prev_button)
        btnPrev.setOnClickListener {
            try {
                if (!loadingData && model.logbookPages.value != null && model.currentLBIndex.value != null) {
                    loadingData = true
                    val trainingId = model.trainingId.value
                    val exerciseId = model.exerciseId.value
                    val logbookPages = model.logbookPages.value

                    val previousIndex = model.currentLBIndex.value!! - 1
                    val previousLogbookPage =
                        if (previousIndex >= 0 && previousIndex < logbookPages!!.size) logbookPages!![previousIndex] else null

                    if (previousLogbookPage != null) {
                        Log.d("Err", "AAA")
                        model.currentLBIndex.value = previousIndex

                        mainViewModel.getTrainingEff(Integer(previousLogbookPage.id))
                        mainViewModel.trainingEffResponse.observe(
                            viewLifecycleOwner,
                            Observer { response ->
                                val effTrainings = response
                                var effTraining: TrainingEff? = null

                                effTrainings.forEach {
                                    if (it.trainingId == trainingId) effTraining = it
                                }

                                if (effTraining != null) {
                                    Log.d("Err", "BBB")

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
                                                val pattern = Regex("\\d{4}-\\d{2}-\\d{2}")
                                                val formattedDate =
                                                    pattern.find(effExercise!!.createdAt!!, 0)
                                                DateTitle.text = formattedDate?.value ?: "Error"

                                                Log.d("Err", "CCC")

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
                                                        loadingData = false
                                                    })
                                            } else {
                                                DateTitle.text = "No data"
                                                loadingData = false
                                                Log.d("Err", "No effExercise found")
                                            }
                                        })
                                } else {
                                    DateTitle.text = "No data"
                                    loadingData = false
                                    Log.d("Err", "No effTraining found")
                                }
                            })
                    } else {
                        loadingData = false
                        Log.d("Err", "No previous lastbookpage found")
                    }
                }
            } catch (e: Exception) {
                Log.d("EXCEPTION", e.toString())
            }
        }

        // Next button
        val btnNext: Button = root.findViewById(R.id.next_button)
        btnNext.setOnClickListener {
            try {
                if (!loadingData && model.logbookPages.value != null && model.currentLBIndex.value != null) {
                    loadingData = true
                    val trainingId = model.trainingId.value
                    val exerciseId = model.exerciseId.value
                    val logbookPages = model.logbookPages.value

                    val nextIndex = model.currentLBIndex.value!! + 1
                    val nextLogbookPage =
                        if (nextIndex >= 0 && nextIndex < logbookPages!!.size) logbookPages!![nextIndex] else null

                    if (nextLogbookPage != null) {
                        model.currentLBIndex.value = nextIndex

                        mainViewModel.getTrainingEff(Integer(nextLogbookPage.id))
                        mainViewModel.trainingEffResponse.observe(
                            viewLifecycleOwner,
                            Observer { response ->
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
                                                val pattern = Regex("\\d{4}-\\d{2}-\\d{2}")
                                                val formattedDate =
                                                    pattern.find(effExercise!!.createdAt!!, 0)
                                                DateTitle.text = formattedDate?.value ?: "Error"

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
                                                        loadingData = false
                                                    })
                                            } else {
                                                DateTitle.text = "No data"
                                                loadingData = false
                                                Log.d("Err", "No effExercise found")
                                            }
                                        })
                                } else {
                                    DateTitle.text = "No data"
                                    loadingData = false
                                    Log.d("Err", "No effTraining found")
                                }
                            })
                    } else {
                        loadingData = false
                        Log.d("Err", "No next lastbookpage found")
                    }
                }
            } catch (e: Exception) {
                Log.d("EXCEPTION", e.toString())
            }
        }

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
            model.logbookPages.value = logbookPages

            val currentLBIndex = if (logbookPages.size < 2) 0 else logbookPages.lastIndex - 1
            val pageBeforeLastLogbookPage =
                if (logbookPages.size < 2) null else logbookPages[currentLBIndex]

            if (pageBeforeLastLogbookPage != null) {
                model.currentLBIndex.value = currentLBIndex

                mainViewModel.getTrainingEff(Integer(pageBeforeLastLogbookPage.id))
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
                                    val pattern = Regex("\\d{4}-\\d{2}-\\d{2}")
                                    val formattedDate = pattern.find(effExercise!!.createdAt!!, 0)
                                    DateTitle.text = formattedDate?.value ?: "Error"

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
                                            loadingData = false
                                        })
                                } else {
                                    DateTitle.text = "No data"
                                    loadingData = false
                                    Log.d("Err", "No effExercise found")
                                }
                            })
                    } else {
                        DateTitle.text = "No data"
                        loadingData = false
                        Log.d("Err", "No effTraining found")
                    }
                })
            } else {
                DateTitle.text = "No data"
                loadingData = false
                Log.d("Err", "No lastbookpage found")
            }
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