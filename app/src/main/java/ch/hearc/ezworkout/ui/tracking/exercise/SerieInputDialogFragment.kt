package ch.hearc.ezworkout.ui.tracking.exercise

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.networking.MainViewModel
import ch.hearc.ezworkout.networking.MainViewModelFactory
import ch.hearc.ezworkout.networking.repository.Repository

class SerieInputDialogFragment : DialogFragment() {

    private val model: ExerciseViewModel by activityViewModels()
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            // Construct dialog using a builder class
            val builder = AlertDialog.Builder(it)

            val inflater = requireActivity().layoutInflater;

            val root = inflater.inflate(R.layout.a_e_serie_input_dialog_fragment, null)

            val pos = arguments?.getInt("pos")!!
            val serieTxt = "Serie$pos"
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

                        val serieId = SerieContent.ITEMS[pos - 1].id
                        var kg = "0"
                        var rep = "1"
                        if (!kgEdit.text.isNullOrEmpty())
                        {
                            kg = kgEdit.text.toString()
                        }
                        if (!repsEdit.text.isNullOrEmpty())
                        {
                            rep = repsEdit.text.toString()
                        }

                        if (serieId == -1) {

                            model.serieCountEff.value = model.serieCountEff.value!! + 1
                            SerieContent.editItem(serieId, pos, kg, rep)

                        } else {

                            SerieContent.editItem(serieId, pos, kg, rep)
                        }

                        (it.supportFragmentManager.findFragmentById(R.id.exercise_today_fragment) as ExerciseTodayFragment)
                            .myAdapter.notifyDataSetChanged()

                        model.currentSeriePos.value = pos

                        // Finishes the activity when the exercise is finished
                        if( model.currentSerieIndex.value!! >= model.serieCount.value!!)
                        {
                            it.finish()
                        }
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