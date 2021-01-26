package ch.hearc.ezworkout.ui.activities.exercise

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.networking.MainViewModel
import ch.hearc.ezworkout.networking.MainViewModelFactory
import ch.hearc.ezworkout.networking.model.ExerciseEff
import ch.hearc.ezworkout.networking.model.SeriesEff
import ch.hearc.ezworkout.networking.repository.Repository
import kotlinx.android.synthetic.main.a_e_serie_input_dialog_fragment.*
import ch.hearc.ezworkout.ui.activities.exercise.SerieListFragment
import kotlinx.android.synthetic.main.a_e_exercise_activity.*

class SerieInputDialogFragment : DialogFragment() {

    private val model: ExerciseViewModel by activityViewModels()
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)

            val inflater = requireActivity().layoutInflater;

            val root = inflater.inflate(R.layout.a_e_serie_input_dialog_fragment, null)

            val serieId = arguments?.getInt("serie")!!
            val serieTxt = "Serie" + serieId.toString()
            root.findViewById<TextView>(R.id.serieNb).setText(serieTxt)
            val kgEdit = root.findViewById<EditText>(R.id.kg)
            val repsEdit = root.findViewById<EditText>(R.id.reps)

            val sharedPref = PreferenceManager.getDefaultSharedPreferences(activity)
            // Access db data local model
            mainViewModel = ViewModelProvider(
                this,
                MainViewModelFactory(Repository(sharedPref))
            ).get(MainViewModel::class.java)


            builder.setView(root)
                .setPositiveButton("Validate",
                    DialogInterface.OnClickListener { dialog, id ->

                        val kg = kgEdit.text.toString()
                        val rep = repsEdit.text.toString()

                        SerieContent.editItem(serieId, kg , rep)


                        if (model.serieCountEff.value!! < serieId)
                        {
                            Log.d("Bro", "serieCountEff")
                            Log.d("Bro", model.serieCountEff.value.toString())
                            Log.d("Bro", "exerciseEffId")
                            Log.d("Bro", model.exerciseEffId.value.toString())

                            val seriesEff = SeriesEff()
                            seriesEff.exerciseEffId = model.exerciseEffId.value!!
                            seriesEff.rep = rep.toInt()
                            seriesEff.weight = kg.toInt()
                            seriesEff.pause = 0

                            Log.d("Bro", "seriesEff")
                            Log.d("Bro", seriesEff.toString())

                            mainViewModel.addSeriesEff(seriesEff)
                        }

                        (it.supportFragmentManager.findFragmentById(R.id.exercise_today_fragment) as ExerciseTodayFragment)
                            .myAdapter.notifyDataSetChanged()
                    })
                .setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        getDialog()?.cancel()
                    })

            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}