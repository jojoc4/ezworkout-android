package ch.hearc.ezworkout.ui.planning.trainingDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.networking.MainViewModel
import ch.hearc.ezworkout.networking.MainViewModelFactory
import ch.hearc.ezworkout.networking.repository.Repository
import java.util.*

/**
 * A fragment representing a list of Items.
 */
class FragmentExercise : Fragment() {
    var trainingId = 0
    private var columnCount = 1
    val items: MutableList<Ex> = ArrayList()

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
        val view = inflater.inflate(R.layout.fragment_exercise_list, container, false)
        trainingId = requireArguments().getInt("Trid")

        var viewModel = ViewModelProvider(this,
            MainViewModelFactory(Repository(PreferenceManager.getDefaultSharedPreferences(activity)))
        ).get(MainViewModel::class.java)

        viewModel.getExercise(Integer(trainingId))
        viewModel.exerciseResponse.observe(viewLifecycleOwner, Observer { response ->
            items.clear()
            for (ex in response) {
                ex.name?.let { FragmentExercise.Ex(ex.id, it) }?.let { items.add(it) }
            }

            // Set the adapter
            if (view is RecyclerView) {
                with(view) {
                    layoutManager = when {
                        columnCount <= 1 -> LinearLayoutManager(context)
                        else -> GridLayoutManager(context, columnCount)
                    }
                    adapter = activity?.let { ExerciseRecyclerViewAdapter(items, it, trainingId) }
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

        viewModel.getExercise(Integer(trainingId))
    }

    data class Ex(val id: Int, val content: String) {
        override fun toString(): String = content
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            FragmentExercise().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}