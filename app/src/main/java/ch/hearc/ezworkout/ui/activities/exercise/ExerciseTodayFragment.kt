package ch.hearc.ezworkout.ui.activities.exercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.hearc.ezworkout.R

/**
 * A simple [Fragment] subclass.
 * Use the [ExerciseTodayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExerciseTodayFragment : Fragment() {

    private val model: ExerciseViewModel by activityViewModels()

    lateinit var myAdapter: ExerciseRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        myAdapter = ExerciseRecyclerViewAdapter(SerieContent.ITEMS, model)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.a_e_exercise_today_fragment, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ExerciseTodayFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            ExerciseTodayFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}