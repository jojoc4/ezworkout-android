package ch.hearc.ezworkout.ui.tracking.training

import java.util.*

object ExerciseContent {

    /**
     * An array of exercise items.
     */
    val ITEMS: MutableList<ExerciseItem> = ArrayList()

    /**
     * A map of exercise items, by ID.
     */
    val ITEM_MAP: MutableMap<Int, ExerciseItem> = HashMap()

    fun addItem(item: ExerciseItem) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
    }

    fun createExerciseItem(id: Int, label: String, skipped: Boolean, serieCount: Int): ExerciseItem {
        return ExerciseItem(id, label, skipped, serieCount)
    }

    /**
     * A exercise item representing a piece of content.
     */
    data class ExerciseItem(val id: Int, val label: String, var skipped: Boolean, var serieCount: Int) {
        override fun toString(): String = label
    }
}