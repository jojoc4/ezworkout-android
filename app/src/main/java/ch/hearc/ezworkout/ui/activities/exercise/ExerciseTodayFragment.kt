package ch.hearc.ezworkout.ui.activities.exercise

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.networking.MainViewModel
import ch.hearc.ezworkout.networking.MainViewModelFactory
import ch.hearc.ezworkout.networking.model.SeriesEff
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

        Log.d("Bro - serieCount ", model.serieCount.value!!.toString())

        myAdapter = ExerciseRecyclerViewAdapter(SerieContent.ITEMS, model)

        mainViewModel.getExerciseEff(Integer(model.trainingEffId.value!!))

        mainViewModel.exerciseEffResponse.observe(viewLifecycleOwner, { response ->
            if (response.isNotEmpty()) {

                response.forEach {
                    if (it.exerciseId == model.exerciseId.value) {
                        model.exerciseEffId.value = it.id
                        Log.d("Bro - exerciseEffId", model.exerciseEffId.value.toString())
                    }
                }

                if (model.exerciseEffId.value != null) {

                    mainViewModel.getSeriesEff(Integer(model.exerciseEffId.value!!))

                    mainViewModel.seriesEffResponse.observe(viewLifecycleOwner, { response ->

                        Log.d("Bro - serieCountEff", response.count().toString())
                        model.serieCountEff.value = response.count()

                        SerieContent.ITEMS.clear()
                        SerieContent.ITEM_MAP.clear()

                        for (i in 1..model.serieCount.value!!) {
                            if (i <= model.serieCountEff.value!!) {

                                Log.d("Bro - Fragment - response1", response[i-1].id.toString())

                                val currentSerie = response[i - 1]
                                SerieContent.addItem(
                                    SerieContent.createSerieItem(
                                        currentSerie.id,
                                        i,
                                        currentSerie.weight.toString(),
                                        currentSerie.rep.toString()
                                    )
                                )
                            } else {
                                SerieContent.addItem(
                                    SerieContent.createSerieItem(
                                        -1,
                                        i,
                                        "__",
                                        "__"
                                    )
                                )
                            }
                        }
                        Log.d("Bro - Fragment - SerieContent", SerieContent.ITEMS.toString())
                        myAdapter.notifyDataSetChanged()
                    })
                }
            }
        })

        model.currentSeriePos.observe(viewLifecycleOwner, Observer {

            val pos = model.currentSeriePos.value!!
            val currentSerie: SerieContent.SerieItem =
                SerieContent.ITEMS[pos-1]
            val serieId = currentSerie.id
            val kg = currentSerie.kg
            val rep = currentSerie.reps

            Log.d("Bro - Fragment - currentSerie", currentSerie.toString())

            if (serieId == -1) {
                //Log.d("Bro - Today - add - pos", pos.toString())

                val seriesEff = SeriesEff()
                seriesEff.exerciseEffId = model.exerciseEffId.value!!
                seriesEff.rep = rep.toInt()
                seriesEff.weight = kg.toInt()
                seriesEff.pause = 0

                mainViewModel.addSeriesEff(seriesEff)

                mainViewModel.newSeriesEffResponse.observe(viewLifecycleOwner,
                    Observer { response ->
                        Log.d("Bro - Today - add", "yo")
                        model.serieCountEff.value = model.serieCountEff.value!! + 1
                        SerieContent.editItem(response.id, pos, kg, rep)
                        myAdapter.notifyDataSetChanged()
                        mainViewModel.newSeriesEffResponse.removeObservers(viewLifecycleOwner)
                    })
                mainViewModel.addSeriesEff(seriesEff)
            } else {
                //Log.d("Bro - Today - update - pos", pos.toString())

                La requête de ses morts ne se relance pas bien que le serieId ait changé
                Log.d("Bro - Today - update id", serieId.toString())
                mainViewModel.getSeriesEff(serieId)

                mainViewModel.oneSeriesEffResponse.observe(
                    viewLifecycleOwner,
                    Observer { response ->

                        Log.d("Bro - Today - update response", response.id.toString())
                        Log.d("Bro - Today - update response", response.rep.toString())
                        Log.d("Bro - Today - update response", response.weight.toString())

                        response.weight = kg.toInt()
                        response.rep = rep.toInt()
                        response.pause = 0

                        Log.d("Bro - Today - update response2", response.id.toString())
                        Log.d("Bro - Today - update response2", response.rep.toString())
                        Log.d("Bro - Today - update response2", response.weight.toString())

                        mainViewModel.updateSeriesEff(response)

                        mainViewModel.oneSeriesEffResponse.removeObservers(viewLifecycleOwner)
                    })
            }
        })

        /*
            val serieId = SerieContent . ITEM_MAP [pos]!!.id
        val kg = kgEdit.text.toString()
        val rep = repsEdit.text.toString()

        if (serieId == -1) {
            Log.d("Bro - serieCountEff - add", model.serieCountEff.value.toString())

            val seriesEff = SeriesEff()
            seriesEff.exerciseEffId = model.exerciseEffId.value!!
            seriesEff.rep = rep.toInt()
            seriesEff.weight = kg.toInt()
            seriesEff.pause = 0

            mainViewModel.addSeriesEff(seriesEff)

            mainViewModel.newSeriesEffResponse.observe((it.supportFragmentManager.findFragmentById(
                R.id.exercise_today_fragment
            ) as ExerciseTodayFragment).viewLifecycleOwner,
                Observer { response ->
                    Log.d("Bro - serieCountEff - add", "yo")
                    model.serieCountEff.value = model.serieCountEff.value!! + 1
                    SerieContent.editItem(response.id, pos, kg, rep)
                    //mainViewModel.newSeriesEffResponse.removeObservers(this)  }
                })
        } else {
            Log.d(
                "Bro - serieCountEff - update",
                model.serieCountEff.value.toString()
            )

            mainViewModel.getSeriesEff(SerieContent.ITEM_MAP[pos]!!.id)

            mainViewModel.oneSeriesEffResponse.observe(
                (it.supportFragmentManager.findFragmentById(R.id.exercise_today_fragment) as ExerciseTodayFragment).viewLifecycleOwner,
                Observer { response ->

                    Log.d("Bro - serieCountEff - update", "yo")

                    response.weight = kg.toInt()
                    response.rep = rep.toInt()
                    response.pause = 0

                    mainViewModel.updateSeriesEff(response)

                    SerieContent.editItem(serieId, pos, kg, rep)

                    /*mainViewModel.oneSeriesEffResponse.removeObservers(
                        this
                    )*/
                })
        }*/
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