package com.example.bmicalculator.addnewbmi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bmicalculator.Event
import com.example.bmicalculator.R
import com.example.bmicalculator.models.BMI
import com.example.bmicalculator.utils.getNowInString
import java.util.*

class AddNewBmiViewModel : ViewModel() {

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText

    private val _calculateBmiEvent = MutableLiveData<Event<Unit>>()
    val calculateBmiEvent: LiveData<Event<Unit>> = _calculateBmiEvent

    val height = MutableLiveData<String>()
    val weight = MutableLiveData<String>()
    lateinit var bmi: BMI

    private fun checkValueOfCorrectness(height: String?, weight: String?): Boolean {
        return when {
            height.isNullOrEmpty() -> {
                _snackbarText.value = Event("height_is_empty")
                false
            }
            weight.isNullOrEmpty() -> {
                _snackbarText.value = Event("weight_is_empty")
                false
            }
            else -> {
                true
            }
        }
    }

    private fun createBmiInstance(height: String, weight: String) = BMI(
        0,
        height.toInt(),
        weight.toInt(),
        Date().getNowInString()
    )

    fun calculateBmi() {
        val currentHeight = height.value
        val currentWeight = weight.value

        if (checkValueOfCorrectness(currentHeight, currentWeight)) {
            bmi = createBmiInstance(currentHeight!!, currentWeight!!)
            _calculateBmiEvent.value = Event(Unit)
        }
    }
}
