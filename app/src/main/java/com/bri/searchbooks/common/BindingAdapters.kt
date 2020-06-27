package com.bri.searchbooks.common

import android.content.Context
import android.graphics.Paint
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
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
        list?.let { sb.append(it.joinToString("\n")) }
        v.text = sb.toString()
    }


    @JvmStatic
    @BindingAdapter("app:setVisible")
    fun setVisible(v: View, data: Any?) {
        data?.let {
            when (data) {
                is String -> v.visibility = if (data.isEmpty()) View.GONE else View.VISIBLE
                is Collection<*> -> v.visibility = if (data.isEmpty()) View.GONE else View.VISIBLE
                else -> View.GONE
            }
        } ?: run { v.visibility = View.GONE }
    }

    @JvmStatic
    @BindingAdapter("app:setRelatedEditText")
    fun setRelatedEditText(v: View, editText: EditText) {
        v.setOnClickListener { editText.text.clear() }
    }

    @JvmStatic
    @BindingAdapter("app:setOnClick")
    fun setOnClick(v: View, function: () -> Unit) {
        v.setOnClickListener { function() }
    }

    @JvmStatic
    @BindingAdapter("app:setStrikeThrough")
    fun setStrikeThrough(v: TextView, flag: Boolean) {
        if(flag) v.paintFlags = v.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }

    @JvmStatic
    @BindingAdapter("app:noBreakText")
    fun noBreakText(v: TextView, string: String?) {
        string?.let {
            v.text = it.replace(" ", "\u00A0").replace("-", "\u2011").replace("/", "\u2215")
        }
    }

    @JvmStatic
    @BindingAdapter("app:setEnterFunction", "app:setRelatedRecyclerView")
    fun setEnterFunction(v: EditText, function: () -> Unit, relatedRecyclerView: MainRecyclerView) {
        v.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                function()
                v.hideKeyBoard()
                relatedRecyclerView.myAdapter.resetAnimationIndices()
                return@setOnEditorActionListener true
            } else return@setOnEditorActionListener false
        }
    }
}