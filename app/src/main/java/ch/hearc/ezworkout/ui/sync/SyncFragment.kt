package ch.hearc.ezworkout.ui.sync

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import ch.hearc.ezworkout.R

class SyncFragment : Fragment() {

    private lateinit var syncViewModel: SyncViewModel

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        syncViewModel =
            ViewModelProviders.of(this).get(SyncViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_sync, container, false)

        val sharedPref = activity?.getSharedPreferences(
            "ch.hearc.ezworkout.settingsFile",
            Context.MODE_PRIVATE
        )

        val connected = sharedPref?.getBoolean("connected", false)
        val endpoint = sharedPref?.getString("endpoint", "")
        val api = sharedPref?.getString("api", "")


        val btnScanner: Button = root.findViewById(R.id.scanner)
        val btnSignOff: Button = root.findViewById(R.id.signoff)
        val btnSync: Button = root.findViewById(R.id.sync)
        val textView: TextView = root.findViewById(R.id.text_sync)

        var text: String = if(connected!!){
            "Vous êtes connecté à " + endpoint

        }else{
            "vous n'êtes pas connecté"
        }
        textView.text = text

        if(connected!!){
            btnScanner.visibility = INVISIBLE
        }else{
            btnSync.visibility = INVISIBLE
            btnSignOff.visibility = INVISIBLE
        }

        btnSync.setOnClickListener{
            val toast = Toast.makeText(activity, "synchronisation", Toast.LENGTH_SHORT)
            toast.show()
        }

        btnSignOff.setOnClickListener {
            with(sharedPref?.edit()) {
                putBoolean("connected", false)
                putString("endpoint", "")
                putString("api", "")
                apply()
            }
            activity?.recreate()
        }

        btnScanner.setOnClickListener{
            val intent = Intent(activity, QRReader::class.java)
            startActivity(intent)
        }

        return root
    }



}