package ch.hearc.ezworkout.ui.planning.trainingPlanDetails

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.networking.MainViewModel
import ch.hearc.ezworkout.networking.MainViewModelFactory
import ch.hearc.ezworkout.networking.model.Training
import ch.hearc.ezworkout.networking.model.TrainingPlan
import ch.hearc.ezworkout.networking.repository.Repository
import ch.hearc.ezworkout.ui.planning.trainingDetails.TrainingDetails
import ch.hearc.ezworkout.ui.planning.utils.RenameDialog

class TrainingPlanDetails : AppCompatActivity() {
    lateinit var trainingPlan: TrainingPlan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tp_details)
        setSupportActionBar(findViewById(R.id.toolbar))

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        val TPid = intent.getIntExtra("ch.hearc.ezworkout.TPid", 120)

        getTP(TPid)

        val bundle = bundleOf("TPid" to TPid)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragment_container_view, FragmentTraining::class.java, bundle)
        }

        findViewById<Button>(R.id.rename).setOnClickListener {
            val dialog = RenameDialog()
            dialog.name.value = trainingPlan.name.toString()

            dialog.show(supportFragmentManager, getString(R.string.rename))
            dialog.name.observe(this, {
                val viewModel = ViewModelProvider(this,
                    MainViewModelFactory(Repository(PreferenceManager.getDefaultSharedPreferences(this)))
                ).get(MainViewModel::class.java)
                var updatedTP= TrainingPlan()
                updatedTP.id = TPid
                updatedTP.name = it
                viewModel.updateTrainingPlan(updatedTP)
                this.title =it
            })
        }

        findViewById<Button>(R.id.delete).setOnClickListener {
           if(trainingPlan.id == sharedPref.getInt("currentTPid", 0)){
               Toast.makeText(this, getString(R.string.trainingPlan_delete_error), Toast.LENGTH_LONG).show();
           }else{
               var db = AlertDialog.Builder(this)
               db.setPositiveButton(getString(R.string.delete), DialogInterface.OnClickListener { _, _ ->
                   val viewModel = ViewModelProvider(this,
                       MainViewModelFactory(Repository(PreferenceManager.getDefaultSharedPreferences(this)))
                   ).get(MainViewModel::class.java)
                   viewModel.delTrainingPlan(trainingPlan)
                   finish()
               })
               db.setNegativeButton(getString(R.string.cancel), DialogInterface.OnClickListener { dialog, _ ->
                   dialog.cancel()
               })
               db.setMessage(getString(R.string.trainingPlan_delete_confirmation))
               var ad = db.create()
               ad.show()
           }
        }

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, getString(R.string.trainingPlan_selected), Snackbar.LENGTH_LONG).show()
            with (sharedPref?.edit()) {
                this?.putInt("currentTPid", TPid)
                this?.apply()
            }
        }

        findViewById<Button>(R.id.add).setOnClickListener {
            val dialog = RenameDialog()
            dialog.name.value = ""

            dialog.show( supportFragmentManager, getString(R.string.add))
            dialog.name.observe(this, {
                if(it != "") {
                    val viewModel = ViewModelProvider(this, MainViewModelFactory(Repository(PreferenceManager.getDefaultSharedPreferences(this) ))).get(MainViewModel::class.java)

                    var Tr = Training()
                    Tr.name = it
                    viewModel.addTraining(Tr, TPid)
                    viewModel.newTrainingResponse.observe(this, { response ->
                        val intent = Intent(this, TrainingDetails::class.java).apply {
                            putExtra("ch.hearc.ezworkout.TPid", TPid)
                            putExtra("ch.hearc.ezworkout.Trid", response.id)
                        }
                        ContextCompat.startActivity(this, intent, null)
                    })
                }
            })
        }
    }

    private fun getTP(TPid: Int){
        val viewModel = ViewModelProvider(this,
            MainViewModelFactory(Repository(PreferenceManager.getDefaultSharedPreferences(this)))
        ).get(MainViewModel::class.java)
        viewModel.getTrainingPlan(TPid)
        viewModel.oneTrainingPlanResponse.observe(this, Observer { response ->
            this.title = response.name
            this.trainingPlan = response

        })
    }
}