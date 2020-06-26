package com.bri.searchbooks.view.main.adapter

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bri.searchbooks.R
import com.bri.searchbooks.common.priceFormat
import com.bri.searchbooks.data.Book

object MainBindingAdapters {

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    @BindingAdapter("app:addItems")
    fun addItems(rv: RecyclerView, items: ArrayList<Book>?) {
        items?.let {
            if (rv.adapter == null) rv.adapter = MainAdapter()
            (rv.adapter as? MainAdapter)?.addAll(items)
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
                    if (it.status == onSaleStatusName) salePrice + "원"
                    else originalPrice + "원"
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