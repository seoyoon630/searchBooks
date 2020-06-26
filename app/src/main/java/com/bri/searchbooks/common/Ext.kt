package com.bri.searchbooks.common

import com.bri.searchbooks.net.Net
import java.text.DecimalFormat

inline fun <reified T> createNetService(net: Net): T {
    return net.retrofit.create(T::class.java)
}

fun Int.priceFormat() : String = DecimalFormat("#,###").format(this)