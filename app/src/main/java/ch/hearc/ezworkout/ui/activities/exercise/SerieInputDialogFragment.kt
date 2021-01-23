package ch.hearc.ezworkout.ui.activities.exercise

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import ch.hearc.ezworkout.R
import kotlinx.android.synthetic.main.a_e_serie_input_dialog_fragment.*
import ch.hearc.ezworkout.ui.activities.exercise.SerieListFragment
import kotlinx.android.synthetic.main.a_e_exercise_activity.*

class SerieInputDialogFragment : DialogFragment() {

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

            builder.setView(root)
                .setPositiveButton("Validate",
                    DialogInterface.OnClickListener { dialog, id ->
                        SerieContent.editItem(serieId,
                            kgEdit.text.toString(),
                            repsEdit.text.toString())

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