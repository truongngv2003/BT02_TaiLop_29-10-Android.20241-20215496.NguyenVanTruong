package com.example.bai2

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var buttonShow: Button
    private lateinit var listViewResults: ListView
    private lateinit var textViewError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNumber = findViewById(R.id.editTextNumber)
        radioGroup = findViewById(R.id.radioGroup)
        buttonShow = findViewById(R.id.buttonShow)
        listViewResults = findViewById(R.id.listViewResults)
        textViewError = findViewById(R.id.textViewError)

        buttonShow.setOnClickListener {
            handleButtonShowClick()
        }
    }

    private fun handleButtonShowClick() {
        val input = editTextNumber.text.toString()
        if (input.isEmpty() || !input.matches(Regex("\\d+"))) {
            textViewError.text = "Vui lòng nhập một số nguyên dương."
            textViewError.visibility = View.VISIBLE
            return
        }

        val n = input.toInt()
        if (n > 1_000_000) { //tranh lag out chuong trinh
            textViewError.text = "Vui lòng nhập một số nhỏ hơn 1,000,000."
            textViewError.visibility = View.VISIBLE
            return
        }
        val results = mutableListOf<String>()
        val selectedId = radioGroup.checkedRadioButtonId

        when (selectedId) {
            R.id.radioEven -> {
                for (i in 0..n step 2) {
                    results.add(i.toString())
                }
            }
            R.id.radioOdd -> {
                for (i in 1..n step 2) {
                    results.add(i.toString())
                }
            }
            R.id.radioSquare -> {
                for (i in 0..n) {
                    val square = i * i
                    if (square > n) break
                    results.add(square.toString())
                }
            }
            else -> {
                textViewError.text = "Vui lòng chọn loại số."
                textViewError.visibility = View.VISIBLE
                return
            }
        }

        textViewError.visibility = View.GONE
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, results)
        listViewResults.adapter = adapter
    }
}
