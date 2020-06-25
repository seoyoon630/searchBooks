package com.bri.searchbooks.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bri.searchbooks.base.BaseAdapter
import com.bri.searchbooks.base.BaseHolder
import com.bri.searchbooks.data.Book
import com.bri.searchbooks.databinding.BookitemBinding


class MainAdapter : BaseAdapter<Book>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<Book> {
        val inflater = LayoutInflater.from(parent.context)
        return BookHolder(
            BookitemBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun getItemViewType(position: Int): Int = 0
}

class BookHolder(private val binding: BookitemBinding) : BaseHolder<Book>(binding.root) {
    override fun bind(data: Book) {
        binding.data = data
    }
}