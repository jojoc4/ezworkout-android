package ch.hearc.ezworkout.ui.sync

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.preference.PreferenceManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
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
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                Log.i("SYNCHRONIZETHREAD", "Response: %s".format(response.toString()))
            },
            Response.ErrorListener { error ->
                Log.i("SYNCHRONIZETHREAD", "Erreur")
            }
        )
        queue.add(jsonObjectRequest)

    }
}