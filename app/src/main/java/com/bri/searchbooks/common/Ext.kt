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

inline fun <reified T> createNetService(net: Net): T = net.retrofit.create(T::class.java)

// 로딩바 처리
fun <T> Single<T>.progress(isProgress: MutableLiveData<Boolean>?): Single<T> =
        this.doOnSubscribe { isProgress?.postValue(true) }
                .doOnSuccess { isProgress?.postValue(false) }
                .doOnError { isProgress?.postValue(false) }

// 가상 키보드 올리기
fun View.showKeyBoard() {
    requestFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, 0)
}

// 가상 키보드 내리기
fun View.hideKeyBoard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0);
}

// 금액 표기(33333 -> 33,333원)
fun Int.priceFormat(): String = DecimalFormat("#,###").format(this) + "원"

// 날짜 표기
fun String.format(pattern: String): String {
    val sdf = SimpleDateFormat(pattern, Locale.KOREA)
    val newSdf = SimpleDateFormat("yyyy.MM.dd.", Locale.KOREA)
    val date = sdf.parse(this)
    date?.let { return newSdf.format(it) }
    return ""
}

// 접미사 추가(xml에서 사용)
fun String.addSuffix(suffix: String): String = this + suffix