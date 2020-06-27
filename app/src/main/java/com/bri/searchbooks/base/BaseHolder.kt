package com.bri.searchbooks.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 * Recyclerview Base Holder (BaseAdapter와 함께 사용)
 */
abstract class BaseHolder<VH>(v : View) : RecyclerView.ViewHolder(v) {
    abstract fun bind(data : VH)
}