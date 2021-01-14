package ch.hearc.ezworkout.ui.planification

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.networking.MainViewModel
import ch.hearc.ezworkout.networking.MainViewModelFactory
import ch.hearc.ezworkout.networking.model.TrainingPlan
import ch.hearc.ezworkout.networking.repository.Repository

class RenameDialog : DialogFragment() {
    var TPid: Int = 0
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