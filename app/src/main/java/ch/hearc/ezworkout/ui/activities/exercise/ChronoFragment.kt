package ch.hearc.ezworkout.ui.activities.exercise

import android.content.Context.VIBRATOR_SERVICE
import android.content.SharedPreferences
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
import androidx.fragment.app.activityViewModels
import androidx.preference.PreferenceManager
import ch.hearc.ezworkout.R
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [ChronoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChronoFragment : Fragment() {


    private var StartTimeInMilis:Long = 300000
    private var mTimeLeftInMilis: Long = StartTimeInMilis

    private val model: ExerciseViewModel by activityViewModels()

    private lateinit var mTextViewCountDown: TextView
    private lateinit var mButtonStartPause: Button
    private lateinit var mButtonStop: Button
    private lateinit var mCountDownTimer: CountDownTimer

    private lateinit var sharedPref: SharedPreferences

    private lateinit var vibrator: Vibrator
    private lateinit var vibrationEffect1: VibrationEffect

    private var mTimerRunning: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_chrono, container, false)

        mButtonStartPause = root.findViewById(R.id.button_start_pause)
        mButtonStop = root.findViewById(R.id.button_stop)
        mTextViewCountDown = root.findViewById(R.id.text_view_countdown)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        StartTimeInMilis = model.chronoDurationMilis.value!!
        mTimeLeftInMilis = StartTimeInMilis

        val timings = longArrayOf(0, 1000, 500)
        val amplitudes = intArrayOf(0, VibrationEffect.DEFAULT_AMPLITUDE, 0)
        vibrationEffect1 = VibrationEffect.createWaveform(timings,amplitudes,2)

        sharedPref = PreferenceManager.getDefaultSharedPreferences(activity)
        vibrator = requireActivity().getSystemService(VIBRATOR_SERVICE) as Vibrator

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
                if (sharedPref?.getBoolean("vibration", true)!!)
                {
                    vibrator.vibrate(vibrationEffect1);
                }
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
}