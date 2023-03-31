package com.example.bmicalculator.utils

enum class BmiRanges(var value: Float) {
    UNDERWEIGHT(18.5f),
    HEALTHY(25f),
    OVERWEIGHT(30f)
}

enum class BMIState {
    Underweight,
    Healthy,
    Overweight,
    Obese
}