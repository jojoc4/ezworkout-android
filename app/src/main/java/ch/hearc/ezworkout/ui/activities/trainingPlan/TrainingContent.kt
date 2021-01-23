package ch.hearc.ezworkout.ui.activities.trainingPlan

import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object TrainingContent {

    /**
     * An array of training items.
     */
    val ITEMS: MutableList<TrainingItem> = ArrayList()

    /**
     * A map of training items, by ID.
     */
    val ITEM_MAP: MutableMap<Int, TrainingItem> = HashMap()

    private val COUNT = 25

    init {
        // Add some sample items.
        //addItem(createTrainingItem(1, "Bras jour"))
        //addItem(createTrainingItem(2, "Jambe jour"))
    }

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