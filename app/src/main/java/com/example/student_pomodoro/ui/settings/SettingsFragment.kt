package com.example.student_pomodoro.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.student_pomodoro.R

class SettingsFragment : Fragment() {
    private var workMin = 25
    private var breakMin = 5

    override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_settings,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        val workSeekBar: SeekBar = view.findViewById(R.id.work_seek_bar)
        val workValueText: TextView = view.findViewById(R.id.work_value_text)
        val breakSeekBar: SeekBar = view.findViewById(R.id.break_seek_bar)
        val breakValueText: TextView = view.findViewById(R.id.break_value_text)


        workSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                workMin = progress.coerceIn(5, 60)
                workValueText.text = "$workMin min"

            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}


        })

        breakSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                breakMin = progress.coerceIn(1, 30)
                breakValueText.text = "$breakMin min"

            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })


    }
}