package com.bri.searchbooks.common

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bri.searchbooks.R
import com.bri.searchbooks.base.BaseAdapter
import com.bumptech.glide.Glide
import java.lang.StringBuilder

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("app:adapter")
    fun setAdapter(rv: RecyclerView, adapter: BaseAdapter<Any>) {
        rv.adapter = adapter
    }

    @JvmStatic
    @BindingAdapter("app:src")
    fun setSrc(v: ImageView, drawable: Any?) {
        drawable?.let { Glide.with(v.context).load(it).placeholder(R.drawable.placeholder).error(R.drawable.no_image).into(v) }
    }

    @JvmStatic
    @BindingAdapter("app:listToText")
    fun setListToText(v: TextView, list: ArrayList<String>?) {
        list?.let {
            val sb = StringBuilder()
            sb.append(it.joinToString(", "))
            v.setText(sb.toString())
        }
    }
}