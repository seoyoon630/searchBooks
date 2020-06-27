package com.bri.searchbooks.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.transition.Slide
import com.bri.searchbooks.R
import com.bri.searchbooks.base.BaseFragment
import com.bri.searchbooks.data.Book
import com.bri.searchbooks.databinding.DetailFrBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

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
    }

    companion object {
        fun newInstance(pBook: Book): DetailFragment {
            val bundle = Bundle().apply {
                putParcelable(BOOK, pBook)
            }
            return DetailFragment().apply {
                arguments = bundle
                enterTransition = Slide()
                reenterTransition = null
            }
        }

        const val BOOK = "BOOK"
        const val color = android.R.color.white
    }
}