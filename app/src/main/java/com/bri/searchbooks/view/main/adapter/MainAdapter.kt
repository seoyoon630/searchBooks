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

/**
 * 도서 검색 목록 어댑터
 */
class MainAdapter(val onItemClick: (book: Book) -> Unit) : BaseAdapter<Book?>() {
    // 마지막으로 애니메이션 실행한 뷰 index
    // 애니메이션이 한 번이라도 실행된 뷰는 더이상 실행되지 않도록 하기 위함
    private var _lastAnimatedIndex = -1
    // 마지막으로 시야에 보이는 뷰 index
    private var _lastVisibleIndex = -1
    // 시야에 보이진 않아서 애니메이션이 실행되지 못한 뷰 queue
    private val queueOfView: Queue<Pair<Int, View>> = LinkedList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<Book?> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            0 -> BookHolder(BookItemBinding.inflate(inflater, parent, false))
            else -> EmptyHolder(NoResultBinding.inflate(inflater, parent, false))
        }
    }

    // 검색결과가 없을 때 item이 null로 한 개 들어오도록 설정
    override fun getItemViewType(position: Int): Int = items[position]?.let { 0 } ?: 1

    // item 애니메이션
    private fun animate(v: View, index: Int) {
        // 애니메이션 실행된 뷰보다 아래에 위치한 뷰일 때 경우
        if (_lastAnimatedIndex < index) {
            // bind가 됐지만, 아직 시야에 보이지 않는 경우 queue에 추가
            if (_lastVisibleIndex < index) {
                queueOfView.add(Pair(index, v))
            } else {
                // bind가 됐고, 시야에 보이면 animation start
                v.animation = AnimationUtils.loadAnimation(v.context, R.anim.up_anim)
                _lastAnimatedIndex = index
            }
        } else v.clearAnimation()
    }

    // 검색어 변경 시, 애니메이션 관련 indices 초기화
    fun resetAnimationIndices() {
        _lastAnimatedIndex = -1
        _lastVisibleIndex = -1
        queueOfView.clear()
    }

    // scrollListener onScroll에서 호출
    // scroll 되면서 시야에 보이는 마지막 인덱스를 업데이트하고
    // queue에 있는 뷰들이 시야에 보이기 시작하면, 애니메이션 실행
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
