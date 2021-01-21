package ch.hearc.ezworkout.ui.activities.exercise

import android.content.Context.VIBRATOR_SERVICE
import android.os.Bundle
import android.os.CountDownTimer
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import ch.hearc.ezworkout.R
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [ChronoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChronoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var StartTimeInMilis:Long = 600000 // TODO: receive that in parameter


    private lateinit var mTextViewCountDown: TextView
    private lateinit var mButtonStartPause: Button
    private lateinit var mButtonStop: Button
    private lateinit var mCountDownTimer: CountDownTimer

    val vibrator = requireActivity().getSystemService(VIBRATOR_SERVICE) as Vibrator
    private lateinit var vibrationEffect1: VibrationEffect

    private var mTimerRunning: Boolean = false

    private var mTimeLeftInMilis: Long = StartTimeInMilis


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_chrono, container, false)

        mButtonStartPause = root.findViewById(R.id.button_start_pause)
        mButtonStop = root.findViewById(R.id.button_stop)
        mTextViewCountDown = root.findViewById(R.id.text_view_countdown)

        val timings = longArrayOf(0, 1000, 500)
        val amplitudes = intArrayOf(0, VibrationEffect.DEFAULT_AMPLITUDE, 0)
        vibrationEffect1 = VibrationEffect.createWaveform(timings,amplitudes,2)



        mButtonStartPause.setOnClickListener(View.OnClickListener {
            if (mTimerRunning)
            {
                pauseTimer()
            }else
            {
                startTimer()
            }
        })

        mButtonStop.setOnClickListener(View.OnClickListener {

            stopTimer()
        })
        updateCountDownText()

        return root
    }

    fun startTimer()
    {
        mCountDownTimer = object: CountDownTimer(mTimeLeftInMilis, 1000)
        {
            override fun onTick(millisUntilFinished: Long)
            {
                mTimeLeftInMilis = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish()
            {
                vibrator.vibrate(vibrationEffect1);
            }
        }
        mCountDownTimer.start()
        mTimerRunning = true
        mButtonStartPause.setText("Pause")
        mButtonStop.visibility = View.INVISIBLE
    }

    fun pauseTimer()
    {
        mCountDownTimer.cancel()
        mTimerRunning = false
        mButtonStartPause.setText("Start")
        mButtonStop.visibility = View.VISIBLE
    }

    fun stopTimer()
    {
        mButtonStartPause.visibility = View.INVISIBLE
        vibrator.cancel();

    }

    fun updateCountDownText()
    {
        val minutes: Int =  (mTimeLeftInMilis.toInt() / 1000) / 60
        val seconds: Int =  (mTimeLeftInMilis.toInt() / 1000) % 60

        val timeLeftFormatted:String = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds)
        mTextViewCountDown.setText(timeLeftFormatted)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChronoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChronoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}