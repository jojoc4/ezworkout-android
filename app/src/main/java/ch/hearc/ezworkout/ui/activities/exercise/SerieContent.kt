package ch.hearc.ezworkout.ui.activities.exercise

import android.text.Editable
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
    val ITEM_MAP: MutableMap<String, SerieItem> = HashMap()

    private val COUNT = 5

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(createSerieItem(i, EditText(), 12))
        }
    }

    private fun addItem(item: SerieItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    private fun createSerieItem(id: Int, kg:EditText, serieCount:EditText): SerieItem {
        return SerieItem(id.toString(), kg, serieCount)
    }

    /**
     * A serie item representing a piece of content.
     */
    data class SerieItem(val id: String, val kg: EditText, val serieCount: EditText) {
        val label = "kg    x"
        val content = StringBuilder()
            .append(kg.toString())
            .append(label)
            .append(serieCount.toString()).toString()
        override fun toString(): String = content
    }
}