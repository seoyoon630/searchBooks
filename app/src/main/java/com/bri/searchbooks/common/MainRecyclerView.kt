package com.bri.searchbooks.common

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bri.searchbooks.view.main.MainActivity
import com.bri.searchbooks.view.main.adapter.MainAdapter

class MainRecyclerView : RecyclerView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    val myAdapter: MainAdapter by lazy { MainAdapter { book -> (context as? MainActivity)?.showDetail(book) } }
    val myLayoutManager: LinearLayoutManager by lazy { layoutManager as LinearLayoutManager }

    init {
        adapter = myAdapter
    }

}