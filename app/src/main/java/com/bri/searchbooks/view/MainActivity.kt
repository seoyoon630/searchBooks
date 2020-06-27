package com.bri.searchbooks.view

import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bri.searchbooks.R
import com.bri.searchbooks.base.BaseActivity
import com.bri.searchbooks.data.Book
import com.bri.searchbooks.view.detail.DetailFragment
import com.bri.searchbooks.view.main.MainFragment
import com.bri.searchbooks.view.splash.SplashFragment
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
        // 상세 페이지 상단 back 버튼 처리
        vm.backPressed.observe(this, Observer { onBackPressed() })
        // 상세 페이지 호출
        vm.showDetail.observe(this, Observer { showDetail(it) })

        // splash fragment
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, splashFr)
            commit()
            updateStatusBarColor(SplashFragment.color)
        }

        // 1초 뒤 main fragment
        Handler().postDelayed({
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.container, mainFr)
                commit()
                updateStatusBarColor(MainFragment.color)
            }
        }, 1000)
    }

    // 상세 페이지 호출
    fun showDetail(book: Book) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, DetailFragment.newInstance(book))
            // stack 관리
            addToBackStack(null)
            commit()
            updateStatusBarColor(DetailFragment.color)
        }
    }

    // 페이지 배경색으로 statusbar color 변경
    private fun updateStatusBarColor(@ColorRes colorId: Int) {
        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = ContextCompat.getColor(this@MainActivity, colorId)
        }
    }
}
