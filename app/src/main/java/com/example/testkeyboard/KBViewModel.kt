package com.example.testkeyboard

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

class KBViewModel(application: Application) : AndroidViewModel(application) {
    val str = "SK"
    var _myInt: MutableLiveData<Int> = MutableLiveData()
    init {
        _myInt.value = 1
    }

    val myInt: LiveData<Int>
        get() = _myInt

    fun changeInt() {
        _myInt.value = _myInt.value!!.inc()
    }

    var a = 0
    fun increaseA() {
        a = 3
        println("changed")
    }
//
//    val kbSettingSP = application.applicationContext.getSharedPreferences(
//        "aa",
//        Context.MODE_PRIVATE
//    )
//
//    var time = kbSettingSP.intLiveData("int", 0)
//
//    var double = MediatorLiveData<Int>()
//
//    init {
//        double.addSource(time) {
//            double.value = getDoubleValue()
//        }
//        double.addSource(myInt) {
//            double.value = getDoubleValue()
//        }
//    }
//
//    fun getDoubleValue(): Int {
//        return time.value!!.plus(myInt.value!!)
//    }
}