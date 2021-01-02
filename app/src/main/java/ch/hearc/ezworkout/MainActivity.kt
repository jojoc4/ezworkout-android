package ch.hearc.ezworkout

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import ch.hearc.ezworkout.networking.MainViewModel
import ch.hearc.ezworkout.networking.MainViewModelFactory
import ch.hearc.ezworkout.networking.model.ExerciseEff
import ch.hearc.ezworkout.networking.model.LogbookPage
import ch.hearc.ezworkout.networking.model.SeriesEff
import ch.hearc.ezworkout.networking.model.TrainingEff
import ch.hearc.ezworkout.networking.repository.Repository
import ch.hearc.ezworkout.ui.sync.ConnectActivity
import ch.hearc.ezworkout.ui.sync.QRReader
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val myString = "JE SUIS UNE VARIABLE DE MAIN_ACTIVITY"
    private lateinit var viewModel: MainViewModel

    fun getMyString(): String {
        return myString
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        var sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        val connected = sharedPref.getBoolean("connected", false)
        if(!connected){
            val intent = Intent(this, ConnectActivity::class.java)
            startActivity(intent)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_trainings,
                R.id.navigation_sync
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //test database
        viewModel = ViewModelProvider(this,MainViewModelFactory(Repository(PreferenceManager.getDefaultSharedPreferences(this)))).get(MainViewModel::class.java)

        viewModel.getUser()
        viewModel.userResponse.observe(this, Observer { response ->
            Log.d("--------id-----------",response.id.toString())
            response.name?.let { Log.d("--------name-----------", it) }

        })
    }
}