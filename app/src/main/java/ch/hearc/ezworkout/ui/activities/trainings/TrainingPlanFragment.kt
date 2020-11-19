package ch.hearc.ezworkout.ui.activities.trainings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.ui.activities.training.TrainingContent

/**
 * A fragment representing a list of trainings.
 */
class TrainingPlanFragment : Fragment() {

    private var columnCount = 1

    // Use the 'by activityViewModels()' Kotlin property delegate
    // from the fragment-ktx artifact
    private val model: ATrainingsViewModel by activityViewModels()

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
        val view = inflater.inflate(R.layout.fragment_training_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyTrainingPlanRecyclerViewAdapter(TrainingContent.ITEMS, model)
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
                "DJFKAJDSLKFJDSFLKJDSFAJLDS Clicked on recyclerview training list item:",
                view.toString()
            )
        }
         */
    }

    //{ parent:AdapterView<*>, view:View, position:Int, id:Long ->
    //    val element = parent.getItemAtPosition(position) // The item that was clicked
    //    val action = TrainingPlanFragmentDirections.actionNavigationTrainingPlanToNavigationExercises(element.toString())
    //    findNavController().navigate(action)}


    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            TrainingPlanFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}