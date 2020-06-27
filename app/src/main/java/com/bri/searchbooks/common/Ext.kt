package com.bri.searchbooks.common

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.MutableLiveData
import com.bri.searchbooks.net.Net
import io.reactivex.Single
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

inline fun <reified T> createNetService(net: Net): T {
    return net.retrofit.create(T::class.java)
}

fun <T> Single<T>.progress(isProgress: MutableLiveData<Boolean>?): Single<T> =
        this.doOnSubscribe { isProgress?.postValue(true) }
                .doOnSuccess { isProgress?.postValue(false) }
                .doOnError { isProgress?.postValue(false) }


fun Int.priceFormat(): String = DecimalFormat("#,###").format(this)+"원"

fun View.showKeyBoard() {
    requestFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, 0)
}

fun View.hideKeyBoard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0);
}

fun String.format(pattern: String): String {
    val sdf = SimpleDateFormat(pattern, Locale.KOREA)
    val newSdf = SimpleDateFormat("yyyy.MM.dd.", Locale.KOREA)
    val date = sdf.parse(this)
    date?.let { return newSdf.format(it) }
    return ""
}

fun String.addSuffix(suffix : String) : String = this + suffix