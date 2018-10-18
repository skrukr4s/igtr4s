package com.r4s.igt.app.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.r4s.igt.BR
import com.mobile.support.core.ext.toast
import com.mobile.suppot.databind.ext.observeNotNull
import timber.log.Timber


// Created by sebastian with ♥
abstract class BaseFragment<T : ViewDataBinding, out V : BaseViewModel> : Fragment() {

    protected abstract fun getViewModels(): V?
    protected open fun getBindingVariable(): Int = BR.vm
    @LayoutRes protected abstract fun getLayoutId(): Int

    open fun refresh(): Boolean {
        return false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewDataBinding: T = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        viewDataBinding.setLifecycleOwner(this)
        viewDataBinding.setVariable(getBindingVariable(), getViewModels())
        viewDataBinding.executePendingBindings()
        return viewDataBinding.root
    }


    override fun onStart() {
        super.onStart()
        getViewModels()?.actions?.observeNotNull(this) { onActions(it) }
    }

    open fun onActions(action: Actions) {
        Timber.w(action.toString())
        when (action) {
            is Finish -> getParent<BaseActivity<*, *>>()?.finish()
            is BackPress -> getParent<BaseActivity<*, *>>()?.onBackPressed()
            is Message -> toast(action.content)
        }
    }

    override fun onStop() {
        super.onStop()
        getViewModels()?.actions?.removeObservers(this)
    }

    inline fun <reified T : BaseActivity<*, *>> getParent(): T? = activity as? T

}