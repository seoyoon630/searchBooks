package com.bri.searchbooks.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.transition.Explode
import com.bri.searchbooks.R
import com.bri.searchbooks.base.BaseFragment
import com.bri.searchbooks.databinding.MainFrBinding
import com.bri.searchbooks.view.main.adapter.MainAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class MainFragment : BaseFragment() {
    override val vm: MainViewModel by sharedViewModel()
    private val mActivity: MainActivity? by lazy { activity as? MainActivity }

    private lateinit var binding: MainFrBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fr, container, false)
        return binding.root
    }

    override fun onLoadOnce() {
        super.onLoadOnce()
        binding.vm = vm
        binding.adapter = MainAdapter { book -> mActivity?.showDetail(book) }.apply { addAll(vm.list) }
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment().apply {
                exitTransition = Explode()
            }
        }
    }
}