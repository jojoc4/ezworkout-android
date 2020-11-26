package ch.hearc.ezworkout.ui.sync

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.preference.PreferenceManager
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class SynchronizeThread : Runnable {
    val queue: RequestQueue
    val url: String

    constructor(c: Context, a: Activity) {
        queue = Volley.newRequestQueue(c)
        url = PreferenceManager.getDefaultSharedPreferences(a).getString("endpoint", "").toString()+"/api/"
    }

    override fun run() {
        Log.i("SYNCHRONIZETHREAD", "thread run")
    }
}