package ch.hearc.ezworkout.ui.sync

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import ch.hearc.ezworkout.R

class ConnectActivity : AppCompatActivity() {

    private lateinit var btnScanner: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        var sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        val connected = sharedPref.getBoolean("connected", false)
        if(connected){
            this.finish()
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connect)

        btnScanner = findViewById(R.id.scanner)

        btnScanner.setOnClickListener{
            val intent = Intent(this, QRReader::class.java)
            startActivity(intent)
        }
    }

    override fun onResume(){
        super.onResume()
        var sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        val connected = sharedPref.getBoolean("connected", false)
        if(connected){
            this.finish()
        }
    }
}