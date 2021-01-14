package ch.hearc.ezworkout.ui.planification.TPDetails

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.ui.planification.TrDetails.TrainingDetails

/**
 * [RecyclerView.Adapter] that can display a TRList.
 * TODO: Replace the implementation with code for your data type.
 */
class TrRecyclerViewAdapter(
    private val values: List<fragment_tr.Tr>,
    public val c: Context
) : RecyclerView.Adapter<TrRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_tr, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.contentView.text = item.content
        holder.lay.setOnClickListener {
            val intent = Intent(c, TrainingDetails::class.java).apply {
                putExtra("ch.hearc.ezworkout.Trid", item.id)
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