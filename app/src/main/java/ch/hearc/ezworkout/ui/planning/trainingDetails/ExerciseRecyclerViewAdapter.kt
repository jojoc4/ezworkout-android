package ch.hearc.ezworkout.ui.planning.trainingDetails

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.ui.planning.exerciseDetails.ExerciseDetails


/**
 * [RecyclerView.Adapter] that can display a exercise.
 */
class ExerciseRecyclerViewAdapter(
    private val values: List<FragmentExercise.Ex>,
    public val c: Context,
    public val trid: Int
) : RecyclerView.Adapter<ExerciseRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_exercise, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.contentView.text = item.content
        holder.lay.setOnClickListener {
            val intent = Intent(c, ExerciseDetails::class.java).apply {
                putExtra("ch.hearc.ezworkout.exId", item.id)
                putExtra("ch.hearc.ezworkout.trid", trid)
            }
            ContextCompat.startActivity(c, intent, null)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contentView: TextView = view.findViewById(R.id.content)
        val lay: LinearLayout = view.findViewById(R.id.container)
        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}