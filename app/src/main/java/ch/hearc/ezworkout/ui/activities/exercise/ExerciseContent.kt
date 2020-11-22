package ch.hearc.ezworkout.ui.activities.exercise

import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object ExerciseContent {

    /**
     * An array of exercise items.
     */
    val ITEMS: MutableList<ExerciseItem> = ArrayList()

    /**
     * A map of exercise items, by ID.
     */
    val ITEM_MAP: MutableMap<String, ExerciseItem> = HashMap()

    private val COUNT = 25

    init {
        // Add some sample items.
        addItem(createExerciseItem(1, "Developpe couche"))
        addItem(createExerciseItem(2, "Biceps curl"))
    }

    private fun addItem(item: ExerciseItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    private fun createExerciseItem(id: Int, label: String): ExerciseItem {
        return ExerciseItem(id.toString(), label)
    }

    /**
     * A exercise item representing a piece of content.
     */
    data class ExerciseItem(val id: String, val label: String) {
        override fun toString(): String = label
    }
}