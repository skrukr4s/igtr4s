package com.r4s.igt.utils

import android.app.Activity
import android.content.Intent
import android.text.format.DateFormat
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mobile.support.core.ext.start
import com.r4s.igt.R
import com.r4s.igt.app.App
import com.r4s.igt.ui.main.MainActivity
import timber.log.Timber
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


// Created by sebastian with ♥
//region Activities

fun Activity.startMainActivity() {
    start(MainActivity::class.java) {
        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    }
    finish()
}
//endregion


//region Formats
fun String?.formatDate(dateFormat: String): String{
    val sdf = SimpleDateFormat(dateFormat)
    var date: Date? = null
    try {
        date = sdf.parse(this)
    } catch (e: ParseException) {
        Timber.e("Parse problem: ${e.toString()}")
    }

    val dateFormat = DateFormat.getLongDateFormat(App.appCtx())
    val timeFormat = DateFormat.getTimeFormat(App.appCtx())
    val d = dateFormat.format(date)
    val t = timeFormat.format(date)
    return "$d $t"
}

fun Long?.formatCurrency(currencyFormat: String): String{
    val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
    format.currency = Currency.getInstance(currencyFormat)

    return format.format(this)
}
//endregion


object BindingAdapters {
    @JvmStatic
    @BindingAdapter("avatar")
    fun setImageUrl(v: ImageView, url: String?) {
        if (url == null) {
            v.setImageResource(R.drawable.ci_blue_bl)
            return
        }

        val avatarUrl = url
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.ci_blue_bl)
        Glide.with(v.context)
                .load(avatarUrl)
                .apply(requestOptions)
                .into(v)
    }
}

