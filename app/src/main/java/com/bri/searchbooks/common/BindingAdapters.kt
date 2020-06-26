package com.bri.searchbooks.common

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bri.searchbooks.R
import com.bri.searchbooks.base.BaseAdapter
import com.bumptech.glide.Glide

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("app:src")
    fun setSrc(v: ImageView, drawable: Any?) {
        drawable?.let { Glide.with(v.context).load(it).placeholder(R.drawable.placeholder).error(R.drawable.no_image).into(v) }
    }

    @JvmStatic
    @BindingAdapter("app:listToText")
    fun setListToText(v: TextView, list: ArrayList<String>?) {
        val sb = StringBuilder()
        list?.let { sb.append(it.joinToString(", ")) }
        v.text = sb.toString()
    }

    @JvmStatic
    @BindingAdapter("app:setEnterFunction")
    fun setEnterFunction(v: EditText, function: () -> Unit) {
        v.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                function()
                return@setOnEditorActionListener true
            } else return@setOnEditorActionListener false
        }
    }
}