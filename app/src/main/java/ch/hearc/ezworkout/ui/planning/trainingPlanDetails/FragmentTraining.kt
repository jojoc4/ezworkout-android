package ch.hearc.ezworkout.ui.planning.trainingPlanDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.networking.MainViewModel
import ch.hearc.ezworkout.networking.MainViewModelFactory
import ch.hearc.ezworkout.networking.repository.Repository

/**
 * A fragment representing a list of Items.
 */
class FragmentTraining : Fragment() {
    var trainingPlanId = 0
    val items: MutableList<Tr> = ArrayList()
    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tr_list, container, false)

        trainingPlanId = requireArguments().getInt("TPid")

        var viewModel = ViewModelProvider(this,
            MainViewModelFactory(Repository(PreferenceManager.getDefaultSharedPreferences(activity)))
        ).get(MainViewModel::class.java)

        viewModel.getTraining(Integer(trainingPlanId))
        viewModel.trainingResponse.observe(viewLifecycleOwner, Observer { response ->
            items.clear()
            for (tr in response) {
                tr.name?.let { Tr(tr.id, it) }?.let { items.add(it) }
            }

            // Set the adapter
            if (view is RecyclerView) {
                with(view) {
                    layoutManager = when {
                        columnCount <= 1 -> LinearLayoutManager(context)
                        else -> GridLayoutManager(context, columnCount)
                    }
                    adapter = activity?.let { TrainingRecyclerViewAdapter(items, it, trainingPlanId) }
                }
            }
        })
        return view
    }

    override fun onResume() {
        super.onResume()
        var viewModel = ViewModelProvider(this,
            MainViewModelFactory(Repository(PreferenceManager.getDefaultSharedPreferences(activity)))
        ).get(MainViewModel::class.java)

        viewModel.getTraining(Integer(trainingPlanId))
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            FragmentTraining().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }

    data class Tr(val id: Int, val content: String) {
        override fun toString(): String = content
    }
}