package com.bri.searchbooks.main

import android.os.Bundle
import com.bri.searchbooks.R
import com.bri.searchbooks.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private val vm: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm.getBookList("ads")
    }
}
