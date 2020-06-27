package com.bri.searchbooks.view.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.transition.Fade
import com.bri.searchbooks.R
import com.bri.searchbooks.base.BaseFragment
import com.bri.searchbooks.data.Book
import com.bri.searchbooks.databinding.DetailFrBinding
import com.bri.searchbooks.view.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * 상세 페이지
 */
class DetailFragment : BaseFragment() {
    override val vm: MainViewModel by sharedViewModel()
    private val book: Book? by lazy { arguments?.getParcelable<Book>(BOOK) }

    private lateinit var binding: DetailFrBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fr, container, false)
        return binding.root
    }

    override fun onLoadOnce() {
        super.onLoadOnce()
        binding.book = book
        binding.vm = vm

        vm.openWeb.observe(this, Observer { if (it.isNotEmpty()) openWeb(it) })
    }

    // 외부 브라우저 호출
    private fun openWeb(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        vm.clearUrl()
        startActivity(intent)
    }

    companion object {
        fun newInstance(pBook: Book): DetailFragment {
            val bundle = Bundle().apply {
                putParcelable(BOOK, pBook)
            }
            return DetailFragment().apply {
                arguments = bundle
                enterTransition = Fade()
                reenterTransition = null
            }
        }

        const val BOOK = "BOOK"
        const val color = android.R.color.white
    }
}