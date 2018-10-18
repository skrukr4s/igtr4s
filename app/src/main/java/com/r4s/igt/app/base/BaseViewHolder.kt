package com.r4s.igt.app.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

// Created by sebastian with ♥
abstract class BaseViewHolder<out T : ViewDataBinding, V : BaseViewModel>(private val viewDataBinding: T) : RecyclerView.ViewHolder(viewDataBinding.root) {
    protected abstract fun getViewModel(position: Int): V
    protected abstract fun getBindingVariable(): Int

    open fun onBind(position: Int) {
        viewDataBinding.setVariable(getBindingVariable(), getViewModel(position))
        viewDataBinding.executePendingBindings()
    }
}