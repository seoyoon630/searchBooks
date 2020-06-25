package com.bri.searchbooks.common

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bri.searchbooks.base.BaseAdapter

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("app:adapter")
    fun setAdapter(rv: RecyclerView, adapter: BaseAdapter<Any>) {
        rv.adapter = adapter
    }

}