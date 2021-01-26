package ch.hearc.ezworkout.ui.activities.exercise

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.ui.activities.exercise.SerieContent.SerieItem


/**
 * [RecyclerView.Adapter] that can display a [SerieItem].
 * TODO: Replace the implementation with code for your data type.
 */
class ExerciseRecyclerViewAdapter(
    private val values: List<SerieItem>, private val model: ExerciseViewModel
) : RecyclerView.Adapter<ExerciseRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.a_e_serie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = "Serie " + item.id.toString()
        holder.contentView.text = item.content
        holder.contentView.setOnClickListener(View.OnClickListener { v ->

            if (model.exerciseEffId.value != null) {
                val activity = v.context as AppCompatActivity
                val newDialog = SerieInputDialogFragment()
                val params = Bundle()
                params.putInt("serie", item.id)
                newDialog.arguments = params
                newDialog.show(activity.supportFragmentManager, "Hey")
                this.notifyDataSetChanged()
            }
        })
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.serie_number)
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}