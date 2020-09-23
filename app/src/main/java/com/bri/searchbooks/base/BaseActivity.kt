package com.bri.searchbooks.base

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar

/**
 * BaseActivity
 * BaseViewModel과 연결, 기본적인 로딩바 처리, 메세지 처리를 담당
 */
abstract class BaseActivity : AppCompatActivity() {
    abstract val vm: BaseViewModel?

    private val mProgress by lazy { createProgress() }
    private val rootView by lazy { window.decorView.findViewById<View>(android.R.id.content) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onBind()
        onLoadOnce()
        onLoad()

        vm?.isProgress?.observe(this, Observer { it?.let { isProgress -> if (isProgress) showProgress(); else dismissProgress() } })
        vm?.message?.observe(this, Observer { it?.let { message -> showSnackBar(message = resources.getString(message)) } })
    }

    open fun onBind() {}

    open fun onLoadOnce() {}

    open fun onLoad() {}

    fun reload() {
        onLoad()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissProgress()
    }

    fun showProgress() {
        if (lifecycle.currentState === Lifecycle.State.DESTROYED) return
        if (isFinishing) return
        if (mProgress.isShowing) return

        mProgress.show()
    }

    fun dismissProgress() {
        if (mProgress.isShowing) mProgress.dismiss()
    }

    private fun createProgress(): AppCompatDialog {
        val context = this
        val builder = AlertDialog.Builder(context)
        builder.setView(ProgressBar(context, null, android.R.attr.progressBarStyle))
        val dlg = builder.create()
        dlg.window?.setBackgroundDrawable(ColorDrawable(0x00ff0000))
        dlg.setCanceledOnTouchOutside(false)
        dlg.setCancelable(false)
        return dlg
    }

    fun showSnackBar(message: String?, duration: Int = Snackbar.LENGTH_SHORT, onDismiss: (() -> Unit)? = null) {
        message?.let {
            val snackBar = Snackbar.make(rootView, it, duration)
            onDismiss?.let {
                snackBar.addCallback(object : Snackbar.Callback() {
                    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                        super.onDismissed(transientBottomBar, event)
                        onDismiss()
                    }
                })
            }
            snackBar.show()
        }
    }
}