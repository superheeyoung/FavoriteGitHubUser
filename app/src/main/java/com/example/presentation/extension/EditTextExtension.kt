package com.example.presentation.extension

import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText

inline fun EditText.onSearch(crossinline action: (text: String) -> Unit) {
    imeOptions = EditorInfo.IME_ACTION_SEARCH
    onImeAction {
        hideKeyboard()
        action(text.toString())
    }
}

inline fun EditText.onImeAction(crossinline action: (text: String) -> Unit) {
    setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
        if ((event?.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
            action(text.toString())
            return@OnKeyListener true
        }
        false
    })
    setOnEditorActionListener { _, _, _ ->
        action(text.toString())
        true
    }
}

