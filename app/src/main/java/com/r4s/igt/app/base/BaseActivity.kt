package com.r4s.igt.app.base

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mobile.support.core.SystemUtilsR
import com.mobile.support.core.ext.toast
import com.r4s.igt.BR
import com.r4s.igt.R
import com.r4s.igt.utils.ProgressBarDialog

// Created by sebastian with ♥
abstract class BaseActivity<T : ViewDataBinding, out V : BaseViewModel> : AppCompatActivity() {

    abstract fun getViewModels(): V
    protected open fun getBindingVariable(): Int = BR.vm
    @LayoutRes protected abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(getLayoutId())
        performDataBinding()
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    private fun performDataBinding() {
        val viewDataBinding: T = DataBindingUtil.setContentView(this, getLayoutId())
        viewDataBinding.setLifecycleOwner(this)
        viewDataBinding.setVariable(getBindingVariable(), getViewModels())
        viewDataBinding.executePendingBindings()
    }

    override fun onStart() {
        super.onStart()
        getViewModels().actions.observe(this, Observer { it?.apply { onActions(this) } })
    }

    var progressBarDialog: ProgressBarDialog? = null
    open fun onActions(action: Actions) {
        when (action) {
            is Finish -> finish()
            is BackPress -> onBackPressed()
            is Message -> toast(action.content)
            is Loading -> progressBarDialog = when {
                action.content -> {
                    ProgressBarDialog.getInstance().apply {
                        show(supportFragmentManager, "progress")
                    }
                }
                else -> {
                    progressBarDialog?.dismiss()
                    null
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        getViewModels().actions.removeObservers(this)
    }

    enum class Animation {
        NONE, SLIDE, TRANSITION, PUSH
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (currentFocus != null) {
            SystemUtilsR.hideSoftInput(this)
        }
        return super.dispatchTouchEvent(ev)
    }

    fun pushFragments(fragment: Fragment, content: Int, shouldAnimate: Animation = Animation.NONE, backstack: Boolean = false) {
        val manager = supportFragmentManager

        val ft = manager.beginTransaction()

        when (shouldAnimate) {
            Animation.NONE -> {
            }
            Animation.SLIDE -> {
                ft.setCustomAnimations(R.anim.slide_in_right,
                        R.anim.slide_out_left, R.anim.slide_in_left,
                        R.anim.slide_out_right)
            }
            Animation.PUSH -> {
                ft.setCustomAnimations(
                        R.anim.slide_in_down, R.anim.slide_out_top,
                        R.anim.slide_in_top, R.anim.slide_out_down)
            }
            Animation.TRANSITION -> {
                ft.setCustomAnimations(R.anim.trans_in,
                        R.anim.trans_out, R.anim.trans_in,
                        R.anim.trans_out)
            }
        }

        if (backstack)
            ft.addToBackStack(fragment.javaClass.name)

        ft.replace(content, fragment, fragment.javaClass.name)
        try {
            ft.commit()
            manager.executePendingTransactions()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}


