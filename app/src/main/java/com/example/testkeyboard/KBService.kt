package com.example.testkeyboard

import android.inputmethodservice.InputMethodService
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModelProvider
import com.example.testkeyboard.databinding.QwertykbBinding

class KBService : InputMethodService(), LifecycleOwner {
    lateinit var kbView: View
    lateinit var kbBinding: QwertykbBinding
    private lateinit var mLifecycle: LifecycleRegistry
    private lateinit var vm: KBViewModel
    val myColor = 0xFF7F00FF.toInt()
    var mode = 1
    val mySize = 24.0F
    val s = "S"

    override fun onCreate() {
        super.onCreate()
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(KBViewModel::class.java)
        mLifecycle = LifecycleRegistry(this)
        kbView = layoutInflater.inflate(R.layout.qwertykb, null)
        kbBinding = DataBindingUtil.bind(kbView)!!
        kbBinding.setVariable(BR.kbservice, this)
        kbBinding.lifecycleOwner = this
    }

    override fun onCreateInputView(): View {
        return kbView
    }

    override fun onStartInputView(info: EditorInfo?, restarting: Boolean) {
        Log.d("onStartInputView:", "Started")
    }

    override fun onFinishInputView(finishingInput: Boolean) {
        currentInputConnection.finishComposingText()
        HanguelQWERTY.initChar()
        HanguelQWERTY.initResult()
        HanguelQWERTY.initState()
    }

    override fun onDestroy() {
        super.onDestroy()
        kbBinding.unbind()
    }

    fun makeToast(view: View) {
        Toast.makeText(this.applicationContext, (view as Button).text!!, Toast.LENGTH_SHORT).show()
    }

    override fun getLifecycle(): Lifecycle {
        return mLifecycle
    }

    fun inputChar(button: View) {
        Log.d("INPUTCHAR:", mode.toString())
        val (c1, c2) = HanguelQWERTY.composeChar((button as Button).text!!.single())
        if (c1 != null) {
            currentInputConnection.commitText(c1, 1)
            if (c2 != null) currentInputConnection.setComposingText(c2, 1)
        } else {
            if (c2 != null) currentInputConnection.setComposingText(c2, 1)
        }
    }
}