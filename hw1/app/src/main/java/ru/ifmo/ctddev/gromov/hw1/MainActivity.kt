package ru.ifmo.ctddev.gromov.hw1

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

import org.mariuszgromada.math.mxparser.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        generateButtons()
        if (savedInstanceState != null) DISPLAY.text = savedInstanceState.getString("DATA_DISPLAY")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString("DATA_DISPLAY", "${DISPLAY.text}")
        super.onSaveInstanceState(outState)
    }

    @SuppressLint("SetTextI18n")
    private fun addToDisplay(str: String) {
        if (DISPLAY.text.contains('=')) DISPLAY.text = str
        else DISPLAY.text = "${DISPLAY.text}$str"
    }

    @SuppressLint("SetTextI18n")
    private fun generateButtons() {
        BUTTON_LEFT_BRACKET.setOnClickListener { addToDisplay(getString(R.string.button_left_bracket)) }
        BUTTON_RIGHT_BRACKET.setOnClickListener { addToDisplay(getString(R.string.button_right_bracket)) }
        BUTTON_CLEAR.setOnClickListener { DISPLAY.text = "" }
        BUTTON_BACKSPACE.setOnClickListener { if (DISPLAY.text.isNotEmpty()) DISPLAY.text = DISPLAY.text.substring(0, DISPLAY.text.lastIndex) }
        BUTTON_SEVEN.setOnClickListener { addToDisplay(getString(R.string.button_seven)) }
        BUTTON_EIGHT.setOnClickListener { addToDisplay(getString(R.string.button_eight)) }
        BUTTON_NINE.setOnClickListener { addToDisplay(getString(R.string.button_nine)) }
        BUTTON_DIVIDE.setOnClickListener { addToDisplay(getString(R.string.button_divide)) }
        BUTTON_FOUR.setOnClickListener { addToDisplay(getString(R.string.button_four)) }
        BUTTON_FIVE.setOnClickListener { addToDisplay(getString(R.string.button_five)) }
        BUTTON_SIX.setOnClickListener { addToDisplay(getString(R.string.button_six)) }
        BUTTON_MULTIPLY.setOnClickListener { addToDisplay(getString(R.string.button_multiply)) }
        BUTTON_ONE.setOnClickListener { addToDisplay(getString(R.string.button_one)) }
        BUTTON_TWO.setOnClickListener { addToDisplay(getString(R.string.button_two)) }
        BUTTON_THREE.setOnClickListener { addToDisplay(getString(R.string.button_three)) }
        BUTTON_MINUS.setOnClickListener { addToDisplay(getString(R.string.button_minus)) }
        BUTTON_ZERO.setOnClickListener { addToDisplay(getString(R.string.button_zero)) }
        BUTTON_DOT.setOnClickListener { addToDisplay(getString(R.string.button_dot)) }
        BUTTON_SOLVE.setOnClickListener { addToDisplay("=${Expression((DISPLAY.text as String).replace('×', '*').replace('÷', '/')).calculate()}") }
        BUTTON_PLUS.setOnClickListener { addToDisplay(getString(R.string.button_plus)) }
    }
}
