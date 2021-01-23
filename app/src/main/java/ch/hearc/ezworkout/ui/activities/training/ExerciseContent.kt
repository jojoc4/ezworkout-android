package ch.hearc.ezworkout.ui.activities.training

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
    val ITEM_MAP: MutableMap<Int, ExerciseItem> = HashMap()

    private val COUNT = 25

    init {
        // Add some sample items.
        //addItem(createExerciseItem(1, "Developpe couche"))
        //addItem(createExerciseItem(2, "Biceps curl"))
    }

    fun addItem(item: ExerciseItem) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
    }

    fun createExerciseItem(id: Int, label: String, skipped: Boolean): ExerciseItem {
        return ExerciseItem(id, label, skipped)
    }

    /**
     * A exercise item representing a piece of content.
     */
    data class ExerciseItem(val id: Int, val label: String, var skipped: Boolean) {
        override fun toString(): String = label
    }
}