package com.bri.searchbooks.view.main.adapter

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bri.searchbooks.R
import com.bri.searchbooks.common.MainRecyclerView
import com.bri.searchbooks.common.priceFormat
import com.bri.searchbooks.data.Book

object MainBindingAdapters {
    var itemCount = 0
    var isFinish = true

    @JvmStatic
    @BindingAdapter("app:setScrollListener")
    fun setScrollListener(rv: MainRecyclerView, function: () -> Unit) {
        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var lastVisibleIndex = -1
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastItemIndex = rv.myLayoutManager.findLastVisibleItemPosition()
                // 애니메이션 처리
                if (lastItemIndex > lastVisibleIndex) {
                    lastVisibleIndex = lastItemIndex
                    rv.myAdapter.setLastVisibleIndex(lastItemIndex)
                }
                // 스크롤 최하단에서 5개 미만으로 남았을 때
                if (isFinish && itemCount - lastItemIndex < 5) {
                    function()
                    isFinish = false
                }
            }
        })
    }

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    @BindingAdapter("app:addItems")
    fun addItems(rv: MainRecyclerView, items: ArrayList<Book?>?) {
        items?.let {
            rv.myAdapter.set(items)
            itemCount = rv.myAdapter.itemCount
            isFinish = true
        }
    }

    @JvmStatic
    @BindingAdapter("app:setPrice")
    fun setPrice(v: TextView, book: Book?) {
        book?.let {
            val originalPrice = it.price.priceFormat()
            val salePrice = it.sale_price.priceFormat()
            val onSaleStatusName = v.context.resources.getString(R.string.on_sale)

            v.text =
                    if (it.status == onSaleStatusName) salePrice
                    else originalPrice
        }
    }

    @JvmStatic
    @BindingAdapter("app:isOnSale")
    fun isOnSale(v: View, book: Book?) {
        book?.let {
            val onSaleStatusName = v.context.resources.getString(R.string.on_sale)

            v.alpha =
                    if (it.status == onSaleStatusName) 1f
                    else 0.6f
        }
    }

}