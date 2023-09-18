package com.example.micalculadora

import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    val result: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    private val _errorMsg: MutableLiveData<String> = MutableLiveData()
    val errorMsg: LiveData<String> = _errorMsg
    fun calculateOperation(operation: String): Double {
        if(operation.isEmpty()){
            _errorMsg.value = "Debe digitar dos números"
        } else {

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
            result.value = when (operator) {
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
        return 1.0
    }

}