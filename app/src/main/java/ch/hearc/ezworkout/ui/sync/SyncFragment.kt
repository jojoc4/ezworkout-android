package ch.hearc.ezworkout.ui.sync

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.networking.MainViewModel
import ch.hearc.ezworkout.networking.MainViewModelFactory
import ch.hearc.ezworkout.networking.repository.Repository

/**
 * synchronisation fragment
 * @authorJonatan Baumgartner
 */
class SyncFragment : Fragment() {

    private lateinit var btnSignOff: Button
    private lateinit var textView: TextView

    private lateinit var sharedPref: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_sync, container, false)

        //vars initialisation
        sharedPref = PreferenceManager.getDefaultSharedPreferences(activity)

        btnSignOff = root.findViewById(R.id.signoff)
        textView = root.findViewById(R.id.text_sync)

        val endpoint = sharedPref.getString("endpoint", "")

        textView.text = "Vous êtes connecté à " + endpoint

        var viewModel = ViewModelProvider(this, MainViewModelFactory(Repository(sharedPref))).get(MainViewModel::class.java)

        viewModel.getUser()
        viewModel.userResponse.observe(viewLifecycleOwner, Observer { response ->
            response.name?.let { textView.text = "Bonjour "+ it + "\nVous êtes connecté à " + endpoint }

        })

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