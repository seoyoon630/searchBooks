package com.bri.searchbooks.view.main.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bri.searchbooks.data.Book

object MainBindingAdapters {

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    @BindingAdapter("app:addItems")
    fun addItems(rv: RecyclerView, items: ArrayList<Book>?) {
        items?.let {
            if (rv.adapter == null) rv.adapter = MainAdapter()
            (rv.adapter as? MainAdapter)?.addAll(items)
        }
    }


}