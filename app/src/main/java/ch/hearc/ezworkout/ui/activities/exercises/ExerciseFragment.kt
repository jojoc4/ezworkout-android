package ch.hearc.ezworkout.ui.activities.exercises

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.ui.activities.exercise.ExerciseContent

/**
 * A fragment representing a list of exercises.
 */
class ExerciseFragment : Fragment() {

    private var columnCount = 1

    // Use the 'by activityViewModels()' Kotlin property delegate
    // from the fragment-ktx artifact
    private val model: ExercisesViewModel by activityViewModels()

    override fun onCreate( savedInstanceState: Bundle?) {
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

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyExercisesRecyclerViewAdapter(ExerciseContent.ITEMS, model)
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO : addOnItemTouchListener
        // view.findViewById<RecyclerView>(R.id.list).addOnItemTouchListener()
        /*
        view.findViewById<RecyclerView>(R.id.list).setOnClickListener { view ->
            Log.d(
                "Clicked on recyclerview training list item:",
                view.toString()
            )
        }
         */
    }


    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ExerciseFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}