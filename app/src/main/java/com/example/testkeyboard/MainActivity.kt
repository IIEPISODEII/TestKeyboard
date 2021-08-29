package com.example.testkeyboard

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TimeUtils
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<KBViewModel>()

    val s = "A"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel.myInt.observe(this, {
            Log.d("OBSERVE", "$it")
        })
        findViewById<Button>(R.id.button2).setOnClickListener {
            Log.d("VIEWMODEL:", "${viewModel.a}")
            viewModel.increaseA()
        }
        findViewById<Button>(R.id.button1).setOnClickListener {
            viewModel.a = 2
        }
//        viewModel.double.observe(this, {
//            Log.d("Current Double:", "$it")
//        })
//        viewModel.kbSettingSP.intLiveData("int", 10).observe(this, {
//            Log.d("I'm observing", "SharedPreference in MainActivity $it")
//        })
    }
}