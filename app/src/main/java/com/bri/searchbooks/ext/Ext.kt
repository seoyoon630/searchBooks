package com.bri.searchbooks.ext

import com.bri.searchbooks.net.Net

inline fun <reified T> createNetService(net: Net): T {
    return net.retrofit.create(T::class.java)
}