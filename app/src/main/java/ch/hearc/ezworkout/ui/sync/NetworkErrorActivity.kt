package ch.hearc.ezworkout.ui.sync

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import ch.hearc.ezworkout.MainActivity
import ch.hearc.ezworkout.R

class NetworkErrorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network_error)

        findViewById<Button>(R.id.button).setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}