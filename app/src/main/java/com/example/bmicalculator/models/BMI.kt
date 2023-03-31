package com.example.bmicalculator.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize



@Parcelize
@Entity(tableName = "BMI")
data class BMI(
    @PrimaryKey(autoGenerate = true)
    var bmiId: Int = 0,
    @ColumnInfo(name = "height")
    var height: Int = 0,
    @ColumnInfo(name = "weight")
    var weight: Int = 0,
    @ColumnInfo(name = "date")
    val date: String = ""

) : Parcelable {
    fun bmi(): Float =
        weight.toFloat() /
                ((height.toFloat() / 100) * (height.toFloat() / 100))
}