package ch.hearc.ezworkout.ui.sync

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import ch.hearc.ezworkout.R

/**
 * synchronisation fragment
 * @authorJonatan Baumgartner
 */
class SyncFragment : Fragment() {

    private lateinit var syncViewModel: SyncViewModel

    private lateinit var btnScanner: Button
    private lateinit var btnSignOff: Button
    private lateinit var btnSync: Button
    private lateinit var textView: TextView

    private lateinit var sharedPref: SharedPreferences


    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        syncViewModel =
            ViewModelProviders.of(this).get(SyncViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_sync, container, false)

        //vars initialisation
        sharedPref = activity?.getSharedPreferences("ch.hearc.ezworkout.settingsFile", Context.MODE_PRIVATE)!!

        btnScanner = root.findViewById(R.id.scanner)
        btnSignOff = root.findViewById(R.id.signoff)
        btnSync = root.findViewById(R.id.sync)
        textView = root.findViewById(R.id.text_sync)

        //adapt output to context
       conStateUpdate()

        //add lisners to buttons
        btnSync.setOnClickListener{
            val toast = Toast.makeText(activity, "synchronisation", Toast.LENGTH_SHORT)
            toast.show()
        }

        btnSignOff.setOnClickListener {
            with(sharedPref?.edit()) {
                this?.putBoolean("connected", false)
                this?.putString("endpoint", "")
                this?.putString("api", "")
                this?.apply()
            }
            conStateUpdate()
        }

        btnScanner.setOnClickListener{
            val intent = Intent(activity, QRReader::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onResume() {
        super.onResume()
        conStateUpdate()
    }

    /**
     * adapt output to context by showing the write text and setting the visibility of items
     */
    private fun conStateUpdate(){
        val connected = sharedPref?.getBoolean("connected", false)
        val endpoint = sharedPref?.getString("endpoint", "")
        val api = sharedPref?.getString("api", "")

        var text: String = if(connected!!){
            "Vous êtes connecté à " + endpoint

        }else{
            "vous n'êtes pas connecté"
        }
        textView.text = text

        if(connected!!){
            btnScanner.visibility = INVISIBLE
            btnSync.visibility = VISIBLE
            btnSignOff.visibility = VISIBLE
        }else{
            btnScanner.visibility = VISIBLE
            btnSync.visibility = INVISIBLE
            btnSignOff.visibility = INVISIBLE
        }
    }


}