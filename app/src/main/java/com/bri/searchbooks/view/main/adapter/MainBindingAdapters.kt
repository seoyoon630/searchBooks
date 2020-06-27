package com.bri.searchbooks.view.main.adapter

import android.graphics.Paint
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bri.searchbooks.R
import com.bri.searchbooks.common.MainRecyclerView
import com.bri.searchbooks.common.hideKeyBoard
import com.bri.searchbooks.common.priceFormat
import com.bri.searchbooks.data.Book
import com.bumptech.glide.Glide

object MainBindingAdapters {
    var itemCount = 0
    var isFinish = true

    /**
     * ImageView Glide 연결
     */
    @JvmStatic
    @BindingAdapter("app:src")
    fun setSrc(v: ImageView, drawable: Any?) {
        drawable?.let { Glide.with(v.context).load(it).placeholder(R.drawable.placeholder).error(R.drawable.no_image).into(v) }
    }

    /**
     * TextView ArrayList to String
     */
    @JvmStatic
    @BindingAdapter("app:listToText")
    fun setListToText(v: TextView, list: ArrayList<String>?) {
        v.text = list?.joinToString("\n")
    }

    /**
     * 내용이 없을 때, 라벨도 함께 보이지 않게 하는 함수
     */
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

    /**
     * 해당 뷰를 클릭 시 EditText 내용을 지우는 함수
     */
    @JvmStatic
    @BindingAdapter("app:setRelatedEditText")
    fun setRelatedEditText(v: View, editText: EditText) {
        v.setOnClickListener { editText.text.clear() }
    }

    /**
     * setOnClickListener xml에서 추가
     */
    @JvmStatic
    @BindingAdapter("app:setOnClick")
    fun setOnClick(v: View, function: () -> Unit) {
        v.setOnClickListener { function() }
    }

    /**
     * TextView 취소선 추가
     */
    @JvmStatic
    @BindingAdapter("app:setStrikeThrough")
    fun setStrikeThrough(v: TextView, flag: Boolean) {
        if (flag) v.paintFlags = v.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }

    /**
     * 띄어쓰기로 인한 줄바꿈 방지
     */
    @JvmStatic
    @BindingAdapter("app:noBreakText")
    fun noBreakText(v: TextView, string: String?) {
        string?.let {
            v.text = it.replace(" ", "\u00A0").replace("-", "\u2011").replace("/", "\u2215")
        }
    }

    /**
     * search(enter) 키를 눌렀을 때 함수 호출
     */
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

    /**
     * 도서 목록에서 스크롤 이벤트 처리
     * 1. 애니메이션 = 아이템이 한 개씩 떠오르는 애니메이션 관련 처리
     * (recyclerview에서 item을 bind하는 시점과 실제 애니메이션의 시점 sync 맞추기용)
     * 2. 스크롤 최하단으로부터 5개 미만의 아이템이 남았을 때, 더 불러오기 실행
     */
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

    /**
     * ObservableArrayList를 통해 데이터 갱신 시 RecyclerView 업데이트
     */
    @JvmStatic
    @BindingAdapter("app:addItems")
    fun addItems(rv: MainRecyclerView, items: ArrayList<Book?>?) {
        items?.let {
            rv.myAdapter.set(items)
            // scrollListener에서 현재의 item 개수, 실제 RecyclerView 업데이트 시점을 알아야하기 때문에 업데이트
            itemCount = rv.myAdapter.itemCount
            isFinish = true
        }
    }

    /**
     * 정상판매 중이 아닌 상품은 판매가가 -1으로 나오기 때문에, 해당 제품은 정가를 표시
     * 그 외 상품은 판매가를 표시
     */
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

    /**
     * 정상판매 중이 아닌 상품은 투명도를 0.6으로 줘서 목록에서 다르게 보이도록 설정
     */
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