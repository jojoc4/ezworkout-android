package ch.hearc.ezworkout.ui.activities.exercise

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import ch.hearc.ezworkout.R

import ch.hearc.ezworkout.ui.activities.exercise.SerieContent.SerieItem

/**
 * [RecyclerView.Adapter] that can display a [SerieItem].
 * TODO: Replace the implementation with code for your data type.
 */
class ExerciseRecyclerViewAdapter(
    private val values: List<SerieItem>
) : RecyclerView.Adapter<ExerciseRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.a_e_serie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id
        holder.kgView.text = item.kg
        holder.labelView.text = item.label
        holder.serieCountView.text = item.serieCount
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.serie_number)
        val kgView: EditText = view.findViewById(R.id.kg)
        val labelView: TextView = view.findViewById(R.id.label)
        val serieCountView: EditText = view.findViewById(R.id.serie_count)


        override fun toString(): String {
            return super.toString() + " '" + labelView.text + "'"
        }
    }
}