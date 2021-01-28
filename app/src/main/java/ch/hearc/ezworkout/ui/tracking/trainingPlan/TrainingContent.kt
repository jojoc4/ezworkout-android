package ch.hearc.ezworkout.ui.tracking.trainingPlan

import java.util.*

object TrainingContent {

    /**
     * An array of training items.
     */
    val ITEMS: MutableList<TrainingItem> = ArrayList()

    /**
     * A map of training items, by ID.
     */
    val ITEM_MAP: MutableMap<Int, TrainingItem> = HashMap()

    fun addItem(item: TrainingItem) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
    }

    fun createTrainingItem(id: Int, label: String, skipped: Boolean): TrainingItem {
        return TrainingItem(id, label, skipped)
    }

    /**
     * A training item representing a piece of content.
     */
    data class TrainingItem(val id: Int, val label: String, var skipped: Boolean) {
        override fun toString(): String = label
    }
}