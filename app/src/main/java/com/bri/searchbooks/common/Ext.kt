package com.bri.searchbooks.common

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.bri.searchbooks.net.Net
import java.text.DecimalFormat

inline fun <reified T> createNetService(net: Net): T {
    return net.retrofit.create(T::class.java)
}

fun Int.priceFormat() : String = DecimalFormat("#,###").format(this)


fun View.showKeyBoard() {
    requestFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, 0)
}

fun View.hideKeyBoard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0);
}

