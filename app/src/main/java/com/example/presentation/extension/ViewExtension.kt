package com.example.presentation.extension


import android.content.Intent
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

inline fun <reified T : Any> Context.launchActivity(
    vararg pairs: Pair<String, Any?>
) {
    val intent = newIntent<T>(this)
    intent.putExtras(bundleOf(*pairs))
    startActivity(intent)

}

inline fun <reified T : Any> newIntent(context: Context): Intent =
    Intent(context, T::class.java)