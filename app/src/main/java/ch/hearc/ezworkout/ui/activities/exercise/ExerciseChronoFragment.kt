package ch.hearc.ezworkout.ui.activities.exercise

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.content.SharedPreferences
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.CountDownTimer
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
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
 * Use the [ExerciseChronoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExerciseChronoFragment : Fragment() {

    private var mTimeLeftInMilis: Long = 0

    private val model: ExerciseViewModel by activityViewModels()

    private lateinit var mTextViewCountDown: TextView
    private lateinit var mButtonStartPause: Button
    private lateinit var mButtonStop: Button
    private lateinit var mCountDownTimer: CountDownTimer

    private lateinit var sharedPref: SharedPreferences

    private lateinit var vibrator: Vibrator
    private lateinit var vibrationEffect1: VibrationEffect

    private var mSensorManager: SensorManager? = null
    private var mAccel = 0f // acceleration apart from gravity
    private var mAccelCurrent = 0f // current acceleration including gravity
    private var mAccelLast = 0f// last acceleration including gravity

    private var mShaking = false


    private var mTimerRunning: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.a_e_exercise_chrono_fragment, container, false)

        mButtonStartPause = root.findViewById(R.id.button_start_pause)
        mButtonStop = root.findViewById(R.id.button_stop)
        mTextViewCountDown = root.findViewById(R.id.text_view_countdown)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mTimeLeftInMilis = model.chronoDurationMilis.value!!

        model.chronoDurationReady.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it)
            {
                resetChronoDuration()
                model.chronoEffDurationMilis.value = model.chronoDurationMilis.value
            }
        })


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

        mSensorManager = context?.getSystemService(Context.SENSOR_SERVICE) as SensorManager?;
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
    }

    fun resetChronoDuration()
    {
        mTimeLeftInMilis = model.chronoDurationMilis.value!!
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

        mSensorManager!!.registerListener(mSensorListener, mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    fun pauseTimer()
    {
        if (mTimerRunning) {
            mCountDownTimer.cancel()
            mTimerRunning = false
            mButtonStartPause.setText("Start")
            mButtonStop.visibility = View.VISIBLE

            mSensorManager?.unregisterListener(mSensorListener);
        }
    }

    fun stopTimer()
    {
        mButtonStop.visibility = View.INVISIBLE
        vibrator.cancel()
        model.chronoEffDurationMilis.value =  model.chronoDurationMilis.value!!- mTimeLeftInMilis
        resetChronoDuration()
        Log.d("-------pause----------",model.chronoEffDurationMilis.value.toString())
        model.currentSerieIndex.value = model.currentSerieIndex.value?.plus(1)
        Log.d("-------serie index----------",model.currentSerieIndex.value.toString())
    }

    fun updateCountDownText()
    {
        val minutes: Int =  (mTimeLeftInMilis.toInt() / 1000) / 60
        val seconds: Int =  (mTimeLeftInMilis.toInt() / 1000) % 60

        val timeLeftFormatted:String = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds)
        mTextViewCountDown.setText(timeLeftFormatted)
    }

    private val mSensorListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(se: SensorEvent) {
            val x = se.values[0]
            val y = se.values[1]
            val z = se.values[2]
            mAccelLast = mAccelCurrent
            mAccelCurrent =
                Math.sqrt((x * x + y * y + z * z).toDouble()).toFloat()
            val delta = mAccelCurrent - mAccelLast
            mAccel = mAccel * 0.9f + delta // perform low-cut filter

            if (mAccel >= 5)
            {
                pauseTimer()
                //Log.d("taggle","---------accel---------")
            }
            //Log.d("---------bjr---------",mAccel.toString())
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int)
        {}
    }

    override fun onResume() {
        super.onResume()
        mSensorManager?.registerListener(mSensorListener, mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    override fun onPause() {
        mSensorManager?.unregisterListener(mSensorListener);
        super.onPause()
    }

}