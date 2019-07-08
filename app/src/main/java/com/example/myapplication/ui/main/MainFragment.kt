package com.example.myapplication.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import com.example.myapplication.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class MainFragment : Fragment(), CoroutineScope by MainScope() {

    @ColorRes
    private var selectedColor: Int = R.color.black

    private lateinit var buttonOrange:View
    private lateinit var buttonBlack:View
    private lateinit var buttonBlue:View
    private lateinit var buttonGreen:View
    private lateinit var root:FrameLayout

    private var clicks = 0

    private fun setButtonSelected(view: View, color: Int) {
        selectedColor = color

        arrayOf(buttonBlack, buttonOrange, buttonBlue, buttonGreen).forEach {
            it.alpha = if (view.id == it.id) 0.5F else 1F
        }

        clicks++
    }


    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view =  inflater.inflate(R.layout.main_fragment, container, false)

        root = view.findViewById(R.id.root)
        buttonBlue = view.findViewById(R.id.button_blue)
        buttonOrange = view.findViewById(R.id.button_orange)
        buttonBlack = view.findViewById(R.id.button_black)
        buttonGreen = view.findViewById(R.id.button_green)

        return view
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        root.setOnTouchListener { _, event ->

            if(event.action == MotionEvent.ACTION_UP) {
                val touchX  = event.x
                val touchY = event.y

                Toast.makeText(requireContext(), "Location: $touchX $touchY", Toast.LENGTH_LONG).show()

                val dummyView: View = layoutInflater.inflate(R.layout.dummy_view, root, false)

                dummyView.setBackgroundColor(resources.getColor(selectedColor, null))

                val label = dummyView.findViewById<TextView>(R.id.label)

                root.addView(dummyView)

                label.text = clicks.toString()

                dummyView.x = touchX -50

                dummyView.y = touchY -50
            }

            true
        }

        buttonBlack.setOnClickListener { setButtonSelected(it, R.color.black) }

        buttonOrange.setOnClickListener { setButtonSelected(it, R.color.orange) }

        buttonBlue.setOnClickListener { setButtonSelected(it, R.color.blue) }

        buttonGreen.setOnClickListener { setButtonSelected(it, R.color.green) }

        setButtonSelected(buttonBlack, R.color.black)
    }
}
