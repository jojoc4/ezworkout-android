package ch.hearc.ezworkout.ui.activities.exercise

import android.text.Editable
import android.util.Log
import android.widget.EditText
import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object SerieContent {

    /**
     * An array of sample (serie) items.
     */
    val ITEMS: MutableList<SerieItem> = ArrayList()

    /**
     * A map of sample (serie) items, by ID.
     */
    val ITEM_MAP: MutableMap<Int, SerieItem> = HashMap()

    val COUNT = 5

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(createSerieItem(i, "__", "__"))
        }
    }

    private fun addItem(item: SerieItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

   fun editItem(id: Int, kg:String, reps:String) {

        var kgTxt = kg
        var repsTxt = reps

        if(kg.isNullOrEmpty())
            kgTxt = "__"
        if(reps.isNullOrEmpty())
            repsTxt = "__"
        ITEMS[id - 1] = SerieItem(id, kgTxt, repsTxt) // id - 1 : because ids go from 1 to serieCount
    }

    private fun createSerieItem(id: Int, kg:String, reps:String): SerieItem {
        return SerieItem(id, kg, reps)
    }

    /**
     * A serie item representing a piece of content.
     */
    data class SerieItem(val id: Int, var kg: String, var reps: String) {
        val labelKg = "kg"
        val labelReps = "x"
        var content = StringBuilder()
            .append(reps)
            .append(labelReps)
            .append(kg)
            .append(labelKg)
            .toString()
        override fun toString(): String = content
    }
}