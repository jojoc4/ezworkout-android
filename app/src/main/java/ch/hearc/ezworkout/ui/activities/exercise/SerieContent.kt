package ch.hearc.ezworkout.ui.activities.exercise

import android.text.Editable
import android.util.Log
import android.widget.EditText
import androidx.activity.viewModels
import java.util.ArrayList
import java.util.HashMap

object SerieContent {

    /**
     * An array of sample (serie) items.
     */
    val ITEMS: MutableList<SerieItem> = ArrayList()

    /**
     * A map of sample (serie) items, by ID.
     */
    val ITEM_MAP: MutableMap<Int, SerieItem> = HashMap()

    fun addItem(item: SerieItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.pos, item)
    }

    fun editItem(id: Int, pos: Int, kg:String, reps:String) {
        var kgTxt = kg
        var repsTxt = reps

        if(kg.isNullOrEmpty())
            kgTxt = "0"
        if(reps.isNullOrEmpty())
            repsTxt = "1"

        ITEMS[pos - 1] = SerieItem(id, pos, kgTxt, repsTxt) // id - 1 : because ids go from 1 to serieCount
    }

    fun createSerieItem(id: Int, pos: Int, kg:String, reps:String): SerieItem {
        return SerieItem(id, pos, kg, reps)
    }

    /**
     * A serie item representing a piece of content.
     */
    data class SerieItem(val id: Int, var pos: Int, var kg: String, var reps: String) {
        val labelKg = "kg"
        val labelReps = "x"
        var content = StringBuilder()
            .append(reps)
            .append(labelReps)
            .append(kg)
            .append(labelKg)
            .toString()
    }
}