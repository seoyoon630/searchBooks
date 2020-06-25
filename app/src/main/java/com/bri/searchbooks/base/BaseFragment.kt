package com.bri.searchbooks.base

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parseExtra()
        loadOnce()
        reload()
    }

    protected fun parseExtra() {
        try {
            onParseExtra()
        } catch (ignore: Exception) {
        }
    }

    protected fun loadOnce() {
        onLoadOnce()
    }

    protected fun load() {
        onLoad()
    }

    protected fun clear() {
        onClear()
    }

    protected fun reload() {
        onReload()
    }

    protected open fun onParseExtra() {}
    protected open fun onLoadOnce() {}
    protected open fun onReload() {
        clear()
        load()
    }

    protected open fun onClear() {}
    protected open fun onLoad() {}

    fun showProgress() = (requireActivity() as BaseActivity).showProgress()
    fun dismissProgress() = (requireActivity() as BaseActivity).dismissProgress()
}