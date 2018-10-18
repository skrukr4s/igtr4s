package com.r4s.igt.app.base

import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.r4s.igt.R
import com.r4s.igt.app.App
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

// Created by sebastian with ♥


abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    val actions = MutableLiveData<Actions>()
    val title = MutableLiveData<String>()
    val titleColor = MutableLiveData<Int>()
    val centralIcon = MutableLiveData<Drawable>()
    val actionIconLeft = MutableLiveData<Drawable>()
    val actionIconRight = MutableLiveData<Drawable>()
    var toolbarColor = MutableLiveData<Int>()
    var toolbarShadow = MutableLiveData<Boolean>()

    fun onBackPress() {
        dispatchAction(BackPress)
    }

    init {
        toolbarColor.value = ContextCompat.getColor(App.appCtx(), R.color.a_red)
        toolbarShadow.value = false
        titleColor.value = ContextCompat.getColor(App.appCtx(), R.color.blackBlack  )
    }

    open fun toolbarActionRight() {}
    open fun toolbarActionLeft() {}

    fun dispatchAction(action: Actions) {
        launch(UI) {
            actions.value = action
            actions.value = null
        }
    }

}

