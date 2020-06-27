package com.bri.searchbooks.view.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bri.searchbooks.R

/**
 * 스플래시 페이지
 */
class SplashFragment() : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.splash_fr, container, false)
    }

    companion object {
        fun newInstance(): SplashFragment = SplashFragment()
        const val color = R.color.colorAccent
    }
}