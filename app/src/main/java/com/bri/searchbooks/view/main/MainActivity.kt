package com.bri.searchbooks.view.main

import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import com.bri.searchbooks.R
import com.bri.searchbooks.base.BaseActivity
import com.bri.searchbooks.data.Book
import com.bri.searchbooks.view.main.adapter.MainAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    override val vm: MainViewModel by viewModel()
    private val splashFr: SplashFragment by lazy { SplashFragment.newInstance() }
    private val mainFr: MainFragment by lazy { MainFragment.newInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_act)
    }

    override fun onLoadOnce() {
        super.onLoadOnce()
//        vm.backPressed.observe(this, EventObserver { onBackPressed() })
        vm.showDetail.observe(this, Observer { showDetail(it) })

        // splash
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, splashFr)
            commit()
        }

        // main
        Handler().postDelayed({
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.container, mainFr)
                commit()
            }
        }, 1000)
    }

    fun showDetail(book: Book) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, DetailFragment.newInstance(book))
            addToBackStack(null)
            commit()
        }
    }
}
