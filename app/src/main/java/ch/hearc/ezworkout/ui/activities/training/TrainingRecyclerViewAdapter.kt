package ch.hearc.ezworkout.ui.activities.training

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ch.hearc.ezworkout.R

import ch.hearc.ezworkout.ui.activities.training.ExerciseContent.ExerciseItem
import ch.hearc.ezworkout.ui.activities.trainingPlan.TrainingContent
import ch.hearc.ezworkout.ui.activities.trainingPlan.TrainingPlanRecyclerViewAdapter

/**
 * [RecyclerView.Adapter] that can display a [Exercise].
 * TODO: Replace the implementation with code for your data type.
 */
class TrainingRecyclerViewAdapter(
    private val values: List<ExerciseItem>, private val model: TrainingViewModel
) : RecyclerView.Adapter<TrainingRecyclerViewAdapter.ViewHolder>() {

    private var selectedPos: Int = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.a_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.labelView.text = item.label
        holder.itemView.isSelected = selectedPos == position;

        holder.itemView.setOnClickListener {
            model.select(item)
            notifyItemChanged(selectedPos)
            selectedPos = position
            notifyItemChanged(selectedPos)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val labelView: TextView = view.findViewById(R.id.label)

        override fun toString(): String {
            return super.toString() + " '" + labelView.text + "'"
        }
    }
}