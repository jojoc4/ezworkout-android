package ch.hearc.ezworkout.ui.activities.exercise

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import ch.hearc.ezworkout.R
import kotlinx.android.synthetic.main.a_e_serie_input_dialog_fragment.*

class SerieInputDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)

            val inflater = requireActivity().layoutInflater;

            val root = inflater.inflate(R.layout.a_e_serie_input_dialog_fragment, null)

            val serieTxt = "Serie" + arguments?.getString("serie")
            root.findViewById<TextView>(R.id.serieNb).setText(serieTxt)

            builder.setView(root)
                .setPositiveButton("Validate",
                    DialogInterface.OnClickListener { dialog, id ->
                        Log.d("Info", arguments.toString())
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