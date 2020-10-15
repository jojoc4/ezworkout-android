package ch.hearc.ezworkout.ui.activities.trainings

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ch.hearc.ezworkout.R

import ch.hearc.ezworkout.ui.activities.training.TrainingContent.TrainingItem

/**
 * [RecyclerView.Adapter] that can display a [Training].
 * TODO: Replace the implementation with code for your data type.
 */
class MyTrainingPlanRecyclerViewAdapter(
    private val values: List<TrainingItem>
) : RecyclerView.Adapter<MyTrainingPlanRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_training, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.labelView.text = item.label
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val labelView: TextView = view.findViewById(R.id.label)

        override fun toString(): String {
            return super.toString() + " '" + labelView.text + "'"
        }
    }
}