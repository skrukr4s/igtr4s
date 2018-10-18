package com.r4s.igt.utils

import android.graphics.drawable.Drawable
import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.r4s.igt.R
import com.r4s.igt.app.App


/** Created by sebastian with ♥ on 20.09.2018 **/
object Res {
    fun getAccentColor(): Int = Res.color(R.color.colorAccent)
    fun getPrimaryColor(): Int = Res.color(R.color.colorPrimary)
    fun getPrimaryDarkColor(): Int = Res.color(R.color.colorPrimaryDark)
    fun getBackgroundColor(): Int = Res.color(R.color.white)
    fun color(@ColorRes id: Int): Int = ContextCompat.getColor(App.appCtx(), id)
    fun string(@StringRes id: Int): String = App.appCtx().getString(id)
    fun array(@ArrayRes id: Int): Array<out String>? = App.appCtx().resources.getStringArray(id)
    fun getDrawable(@DrawableRes id: Int): Drawable? = ContextCompat.getDrawable(App.appCtx(), id)
}