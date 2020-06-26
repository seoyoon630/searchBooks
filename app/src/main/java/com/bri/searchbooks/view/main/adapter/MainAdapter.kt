package com.bri.searchbooks.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bri.searchbooks.base.BaseAdapter
import com.bri.searchbooks.base.BaseHolder
import com.bri.searchbooks.data.Book
import com.bri.searchbooks.databinding.BookItemBinding


class MainAdapter(val onItemClick: (book : Book) -> Unit) : BaseAdapter<Book>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<Book> {
        val inflater = LayoutInflater.from(parent.context)
        return BookHolder(
                BookItemBinding.inflate(inflater, parent, false)
        )
    }

    override fun getItemViewType(position: Int): Int = 0

    inner class BookHolder(private val binding: BookItemBinding) : BaseHolder<Book>(binding.root) {
        override fun bind(book: Book) {
            binding.data = book
            binding.root.setOnClickListener { onItemClick(book) }
        }
    }
}
