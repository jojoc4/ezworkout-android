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
import androidx.preference.PreferenceManager
import ch.hearc.ezworkout.R

/**
 * synchronisation fragment
 * @authorJonatan Baumgartner
 */
class SyncFragment : Fragment() {

    private lateinit var syncViewModel: SyncViewModel

    private lateinit var btnSignOff: Button
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
        sharedPref = PreferenceManager.getDefaultSharedPreferences(activity)

        btnSignOff = root.findViewById(R.id.signoff)
        textView = root.findViewById(R.id.text_sync)

        val endpoint = sharedPref.getString("endpoint", "")

        textView.text = "Vous êtes connecté à " + endpoint

        btnSignOff.setOnClickListener {
            with(sharedPref.edit()) {
                this?.putBoolean("connected", false)
                this?.putString("endpoint", "")
                this?.putString("api", "")
                this?.apply()
            }
            val intent = Intent(activity, ConnectActivity::class.java)
            startActivity(intent)
        }

        return root
    }


}