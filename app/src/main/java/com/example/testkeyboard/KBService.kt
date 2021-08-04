package com.example.testkeyboard

import android.inputmethodservice.InputMethodService
import android.text.Layout
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.example.testkeyboard.databinding.QwertykbBinding

class KBService: InputMethodService(), LifecycleOwner {
    lateinit var kbView: View
    lateinit var kbBinding: QwertykbBinding
    lateinit var mLifecycle: LifecycleRegistry

    override fun onCreate() {
        super.onCreate()
        mLifecycle = LifecycleRegistry(this)
    }

    override fun onCreateInputView(): View {
        kbView = layoutInflater.inflate(R.layout.qwertykb, null)
        kbBinding = DataBindingUtil.bind(kbView)!!
        kbBinding.setVariable(BR.kbservice, this)
        kbBinding.lifecycleOwner = this
        return kbView
    }

    val t = "ㅁ"


    override fun onStartInputView(info: EditorInfo?, restarting: Boolean) {
        kbView.findViewById<Button>(R.id.btnQ).setOnClickListener {
            val (c1, c2) = HanguelQWERTY.composeChar(kbView.findViewById<Button>(R.id.btnQ).text.single())
            if (c1 != null) {
                currentInputConnection.commitText(c1, 1)
                if (c2 != null) currentInputConnection.setComposingText(c2, 1)
            } else {
                if (c2 != null) currentInputConnection.setComposingText(c2, 1)
            }
        }
        kbView.findViewById<Button>(R.id.btnY).setOnClickListener {
            val (c1, c2) = HanguelQWERTY.composeChar(kbView.findViewById<Button>(R.id.btnY).text.single())
            if (c1 != null) {
                currentInputConnection.commitText(c1, 1)
                if (c2 != null) currentInputConnection.setComposingText(c2, 1)
            } else {
                if (c2 != null) currentInputConnection.setComposingText(c2, 1)
            }
        }
        kbView.findViewById<ImageButton>(R.id.btnDEL).setOnClickListener {
            val (c1, c2) = HanguelQWERTY.delete()
            if (c1 != null) {
                currentInputConnection.commitText(c1, 1)
                if (c2 != null) currentInputConnection.setComposingText(c2, 1)
            } else {
                if (c2 != null) currentInputConnection.setComposingText(c2, 1)
                else {
                    currentInputConnection.finishComposingText()
                    currentInputConnection.deleteSurroundingText(1, 0)
                }
            }
        }

        kbView.findViewById<Button>(R.id.btnT).setOnClickListener {
            val (c1, c2) = HanguelQWERTY.composeChar(kbView.findViewById<Button>(R.id.btnT).text.single())
            if (c1 != null) {
                currentInputConnection.commitText(c1, 1)
                if (c2 != null) currentInputConnection.setComposingText(c2, 1)
            } else {
                if (c2 != null) currentInputConnection.setComposingText(c2, 1)
            }
        }
    }

    override fun onFinishInputView(finishingInput: Boolean) {
        currentInputConnection.finishComposingText()
        HanguelQWERTY.initChar()
        HanguelQWERTY.initResult()
        HanguelQWERTY.initState()
    }

    fun makeToast(view: View) {
        Toast.makeText(this, "Toasting", Toast.LENGTH_SHORT).show()
        Log.d("What", "is this")
    }

    override fun getLifecycle(): Lifecycle {
        return mLifecycle
    }
}