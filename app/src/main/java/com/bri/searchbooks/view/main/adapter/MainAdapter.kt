package com.bri.searchbooks.view.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.bri.searchbooks.R
import com.bri.searchbooks.base.BaseAdapter
import com.bri.searchbooks.base.BaseHolder
import com.bri.searchbooks.data.Book
import com.bri.searchbooks.databinding.BookItemBinding
import com.bri.searchbooks.databinding.NoResultBinding
import java.util.*


class MainAdapter(val onItemClick: (book: Book) -> Unit) : BaseAdapter<Book?>() {
    private var _lastAnimatedIndex = -1
    private var _lastVisibleIndex = -1
    private val queueOfView: Queue<Pair<Int, View>> = LinkedList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<Book?> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            0 -> BookHolder(BookItemBinding.inflate(inflater, parent, false))
            else -> EmptyHolder(NoResultBinding.inflate(inflater, parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int = items[position]?.let { 0 } ?: 1

    // item 애니메이션
    private fun animate(v: View, index: Int) {
        if (_lastAnimatedIndex < index) {
            if (_lastVisibleIndex < index) {
                queueOfView.add(Pair(index, v))
            } else {
                v.animation = AnimationUtils.loadAnimation(v.context, R.anim.up_anim)
                _lastAnimatedIndex = index
            }
        } else v.clearAnimation()
    }

    fun resetAnimationIndices() {
        _lastAnimatedIndex = -1
        _lastVisibleIndex = -1
        queueOfView.clear()
    }

    fun setLastVisibleIndex(index: Int) {
        _lastVisibleIndex = index
        if (queueOfView.isEmpty())
            return
        val count = if (_lastAnimatedIndex == -1) _lastVisibleIndex + 1 else _lastVisibleIndex - _lastAnimatedIndex
        loop@ for (i in queueOfView.indices) {
            val pair = queueOfView.peek() ?: break@loop
            if (pair.first <= _lastVisibleIndex) {
                queueOfView.poll()?.let { target -> target.second.animation = AnimationUtils.loadAnimation(target.second.context, R.anim.up_anim) }
                _lastAnimatedIndex = index
            } else break@loop
        }
    }

    inner class BookHolder(private val binding: BookItemBinding) : BaseHolder<Book?>(binding.root) {
        override fun bind(data: Book?) {
            data?.let {
                animate(binding.root, items.indexOf(it))
                binding.data = it
                binding.root.setOnClickListener { _ -> onItemClick(it) }
            }

        }
    }

    inner class EmptyHolder(private val binding: NoResultBinding) : BaseHolder<Book?>(binding.root) {
        override fun bind(data: Book?) {
            animate(binding.root, items.indexOf(data))
        }
    }
}
