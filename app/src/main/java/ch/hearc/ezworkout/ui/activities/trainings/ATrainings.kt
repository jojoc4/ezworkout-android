package ch.hearc.ezworkout.ui.activities.trainings

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ch.hearc.ezworkout.R

class ATrainings : Fragment() {

    companion object {
        fun newInstance() = ATrainings()
    }

    private lateinit var viewModel: ATrainingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.a_trainings_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ATrainingsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}