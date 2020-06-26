package com.bri.searchbooks.view.main

import android.os.Bundle
import androidx.lifecycle.Observer
import com.bri.searchbooks.base.BaseActivity
import com.bri.searchbooks.databinding.ActivityMainBinding
import com.bri.searchbooks.view.main.adapter.MainAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    override val vm: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.vm = vm
        setContentView(binding.root)
    }

    override fun onLoad() {
        super.onLoad()
        vm.getBookList("ads")
    }
}
