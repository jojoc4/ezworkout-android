package ch.hearc.ezworkout.ui.planification.TrDetails

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import ch.hearc.ezworkout.R

class RenameDialog : DialogFragment() {
    var name: MutableLiveData<String> = MutableLiveData()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;
            val customLout = inflater.inflate(R.layout.dialog_rename, null)
            builder.setView(customLout)

            builder.setPositiveButton("Enregistrer",
                    DialogInterface.OnClickListener { dialog, id ->
                        name.value = customLout.findViewById<EditText>(R.id.name)?.text.toString()

                    })
                .setNegativeButton("Anuller",
                    DialogInterface.OnClickListener { dialog, id ->
                        getDialog()?.cancel()
                    })
            customLout.findViewById<EditText>(R.id.name)?.setText(name.value)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onStart() {
        super.onStart()
    }
}