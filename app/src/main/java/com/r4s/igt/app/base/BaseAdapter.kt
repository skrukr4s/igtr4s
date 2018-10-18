package com.r4s.igt.app.base

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

interface IDiffUtilable<in T : Any> {
    fun areItemsTheSame(oldItem: T): Boolean
    fun areContentsTheSame(oldItem: T): Boolean
}

interface AdapterNavigator<in T : Any> {
    fun onItemClicked(item: T)
}

abstract class AdapterViewModel<V : Any>(val item: V, val onClick: (Actions) -> Unit) : BaseViewModel()

abstract class BaseAdapter<T : IDiffUtilable<T>, R : BaseViewHolder<*, *>> : ListAdapter<T, R>(object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = newItem.areItemsTheSame(oldItem)
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = newItem.areContentsTheSame(oldItem)
}) {

    abstract fun getViewHolder(parent: ViewGroup): R
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): R = getViewHolder(parent)
    override fun onBindViewHolder(holder: R, position: Int) = holder.onBind(position)

}