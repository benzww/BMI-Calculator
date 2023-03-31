package com.example.bmicalculator.data



import androidx.lifecycle.LiveData
import com.example.bmicalculator.models.BMI

interface BmiRepository {
    val allBmi: LiveData<List<BMI>>

    suspend fun inserBmi(bmi: BMI)
    suspend fun deleteBmi(bmi: BMI)
}