package ch.hearc.ezworkout

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
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
import ch.hearc.ezworkout.networking.api.RetrofitInstance
import ch.hearc.ezworkout.networking.repository.Repository
import ch.hearc.ezworkout.ui.sync.ConnectActivity
import ch.hearc.ezworkout.ui.sync.NetworkErrorActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private val myString = "JE SUIS UNE VARIABLE DE MAIN_ACTIVITY"
    private lateinit var viewModel: MainViewModel
    private var nextQuit = false

    fun getMyString(): String {
        return myString
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //verify that the credential to webapp are saved
        var sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        val connected = sharedPref.getBoolean("connected", false)
        if(!connected){
            val intent = Intent(this, ConnectActivity::class.java)
            startActivity(intent)
        }else{
            //verify that we are connected to the internet
            val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            if(activeNetwork?.isConnected == false){
                val intent = Intent(this, NetworkErrorActivity::class.java)
                startActivity(intent)
            }else{

                RetrofitInstance.url = "https://"+sharedPref.getString("endpoint", "ezw.dev.jojoc4.ch").toString()
                setContentView(R.layout.activity_main)
                val navView: BottomNavigationView = findViewById(R.id.nav_view)

                val navController = findNavController(R.id.nav_host_fragment)
                // Passing each menu ID as a set of Ids because each
                // menu should be considered as top level destinations.
                val appBarConfiguration = AppBarConfiguration(
                    setOf(
                        R.id.navigation_planification,
                        R.id.navigation_trainings,
                        R.id.navigation_sync
                    )
                )
                setupActionBarWithNavController(navController, appBarConfiguration)
                navView.setupWithNavController(navController)


                //test database
                viewModel = ViewModelProvider(
                    this, MainViewModelFactory(
                        Repository(
                            PreferenceManager.getDefaultSharedPreferences(
                                this
                            )
                        )
                    )
                ).get(MainViewModel::class.java)

                viewModel.getUser()
                viewModel.userResponse.observe(this, Observer { response ->
                    Log.d("--------id-----------", response.id.toString())
                    response.name?.let { Log.d("--------name-----------", it) }

                })

            }
        }
    }

    override fun onBackPressed() {
        if (nextQuit) {
            finishAffinity();
        }else {
            nextQuit = true
            Toast.makeText(applicationContext, "Refaire pour quitter", Toast.LENGTH_SHORT).show()
            Handler(Looper.getMainLooper()).postDelayed({
                nextQuit = false
            }, 1000)
        }
    }

    override fun onResume() {
        super.onResume()
        //verify that connection to webapp is ok
        var sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        val connected = sharedPref.getBoolean("connected", false)

        if(!connected){
            val intent = Intent(this, ConnectActivity::class.java)
            startActivity(intent)
        }else {
            val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            if(activeNetwork?.isConnected == false){
                val intent = Intent(this, NetworkErrorActivity::class.java)
                startActivity(intent)
            }
        }
    }
}