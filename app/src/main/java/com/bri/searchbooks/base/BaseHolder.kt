package com.bri.searchbooks.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseHolder<VH>(v : View) : RecyclerView.ViewHolder(v) {
    abstract fun bind(data : VH)
}