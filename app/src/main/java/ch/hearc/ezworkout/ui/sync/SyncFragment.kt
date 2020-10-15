package ch.hearc.ezworkout.ui.sync

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import ch.hearc.ezworkout.R
import ch.hearc.ezworkout.ui.notifications.NotificationsViewModel

class SyncFragment : Fragment() {

    private lateinit var syncViewModel: SyncViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        syncViewModel =
            ViewModelProviders.of(this).get(SyncViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_sync, container, false)
        val textView: TextView = root.findViewById(R.id.text_sync)
        syncViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val btn: Button = root.findViewById(R.id.scanner)
        btn.setOnClickListener{
            val intent = Intent(activity, QRReader::class.java)
            startActivity(intent)
        }

        return root
    }



}