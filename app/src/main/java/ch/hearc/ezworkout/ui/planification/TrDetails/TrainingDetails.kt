package ch.hearc.ezworkout.ui.planification.TrDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.networking.MainViewModel
import ch.hearc.ezworkout.networking.MainViewModelFactory
import ch.hearc.ezworkout.networking.model.Training
import ch.hearc.ezworkout.networking.repository.Repository

class TrainingDetails : AppCompatActivity() {
    lateinit var Tr: Training

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_details)

        val Trid = intent.getIntExtra("ch.hearc.ezworkout.Trid", 120)
        getTr(Trid)

    }

    private fun getTr(Trid: Int){
        val viewModel = ViewModelProvider(this,
            MainViewModelFactory(Repository(PreferenceManager.getDefaultSharedPreferences(this)))
        ).get(MainViewModel::class.java)
        viewModel.getTraining(Trid)
        viewModel.oneTrainingResponse.observe(this, Observer { response ->
            this.title = response.name
            this.Tr = response

        })
    }
}