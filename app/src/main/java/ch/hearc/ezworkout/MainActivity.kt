package ch.hearc.ezworkout

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
import ch.hearc.ezworkout.networking.repository.Repository
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val myString = "JE SUIS UNE VARIABLE DE MAIN_ACTIVITY"
    private lateinit var viewModel: MainViewModel

    fun getMyString(): String {
        return myString
    }

    override fun onCreate(savedInstanceState: Bundle?) {
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

        viewModel.getTrainingPlan()
        viewModel.trainingPlanResponse.observe(this, Observer { response ->
            for (tp in response)
            {
                Log.d("--------id-----------",tp.id.toString())
                tp.name?.let { Log.d("--------name-----------", it) }
                viewModel.getTraining(Integer(tp.id))
                viewModel.trainingResponse.observe(this,{ response ->
                    for(t in response)
                    {
                        Log.d("\t--------id-----------",t.id.toString())
                        t.name?.let { Log.d("\t--------name-----------", it) }
                        viewModel.getExercise(Integer(t.id))
                        viewModel.exerciseResponse.observe(this,{ response ->
                            for(e in response)
                            {
                                Log.d("\t\t--------id-----------",e.id.toString())
                                e.name?.let { Log.d("\t\t--------name-----------", it) }
                            }
                        })
                    }
                })
            }
        })


    }
}