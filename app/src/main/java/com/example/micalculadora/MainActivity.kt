package com.example.micalculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    fun updateText(textView: TextView, num: String, boton: Button) {
        boton.setOnClickListener {
            // Establece el valor del TextView al presionar el botón
            val actualText = textView.text.toString()
            val concatenation = num
            val newText = "$actualText$concatenation" // se realiza una concatenación

            // Establece el nuevo texto en el TextView
            textView.text = newText
        }
    }

    fun calculateOperation(operation: String): Double {
        // Divide la cadena en función del operador (+, -, *, /)
        val fractions = operation.split("[+\\-*/]".toRegex())

        // Solo permite hacer una operación simple
        if (fractions.size != 2) {
            return Double.NaN
        }

        val number_1 = fractions[0].trim().toDoubleOrNull()
        val number_2 = fractions[1].trim().toDoubleOrNull()

        // Error en número nulo
        if (number_1 == null || number_2 == null) {
            return Double.NaN
        }

        // Encuentra el operador en la cadena original
        val operator = operation.find { it in "+-*/" } ?: return Double.NaN

        // Realiza la operación matemática
        return when (operator) {
            '+' -> number_1 + number_2
            '-' -> number_1 - number_2
            '*' -> number_1 * number_2
            '/' -> {
                if (number_2 != 0.0) {
                    number_1 / number_2
                } else {
                    Double.NaN // error división por cero
                }
            }
            else -> Double.NaN
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.result_textView)

        val button_0 = findViewById<Button>(R.id.zero_button)
        updateText(textView,"0",button_0)
        val button_1 = findViewById<Button>(R.id.one_button)
        updateText(textView,"1",button_1)
        val button_2 = findViewById<Button>(R.id.two_button)
        updateText(textView,"2",button_2)
        val button_3 = findViewById<Button>(R.id.three_button)
        updateText(textView,"3",button_3)
        val button_4 = findViewById<Button>(R.id.four_button)
        updateText(textView,"4",button_4)
        val button_5 = findViewById<Button>(R.id.five_button)
        updateText(textView,"5",button_5)
        val button_6 = findViewById<Button>(R.id.six_button)
        updateText(textView,"6",button_6)
        val button_7 = findViewById<Button>(R.id.seven_button)
        updateText(textView,"7",button_7)
        val button_8 = findViewById<Button>(R.id.eight_button)
        updateText(textView,"8",button_8)
        val button_9 = findViewById<Button>(R.id.nine_button)
        updateText(textView,"9",button_9)


        val sum = findViewById<Button>(R.id.addition_button)
        updateText(textView,"+",sum)
        val subtracts = findViewById<Button>(R.id.subtract_button)
        updateText(textView,"-", subtracts)
        val multiplication = findViewById<Button>(R.id.multiplication_button)
        updateText(textView,"*", multiplication)
        val division = findViewById<Button>(R.id.division_button)
        updateText(textView,"/", division)
        val same = findViewById<Button>(R.id.same_button)

        same.setOnClickListener {
            val actualText = textView.text.toString()
            textView.text = (calculateOperation(actualText)).toString()

        }
    }
}
