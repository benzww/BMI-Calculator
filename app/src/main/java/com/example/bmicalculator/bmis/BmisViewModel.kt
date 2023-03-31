package com.example.bmicalculator.bmis

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bmicalculator.Event
import com.example.bmicalculator.data.BmiRepositoryImpl
import com.example.bmicalculator.data.BmiRoomDatabase
import com.example.bmicalculator.models.BMI
import kotlinx.coroutines.launch

class BmisViewModel(application: Application) : AndroidViewModel(application) {

    private val bmiRepositoryImpl: BmiRepositoryImpl
    private val _deleteBmiDialog = MutableLiveData<Event<BMI>>()
    val deleteBmiDialog: LiveData<Event<BMI>> = _deleteBmiDialog

    init {
        val bmidao = BmiRoomDatabase.getDatabase(application.applicationContext).bmiDao()
        bmiRepositoryImpl = BmiRepositoryImpl(bmidao)
    }

    val items: LiveData<List<BMI>> = bmiRepositoryImpl.allBmi

    fun openDeleteBmiDialog(view: View, bmi: BMI): Boolean {
        _deleteBmiDialog.postValue(Event(bmi))
        return false
    }

    fun deleteBmi(bmi: BMI) {
        viewModelScope.launch {
            bmiRepositoryImpl.deleteBmi(bmi)
        }
    }
}
