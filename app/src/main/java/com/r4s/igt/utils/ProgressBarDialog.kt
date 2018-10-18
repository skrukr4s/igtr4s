package com.r4s.igt.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.r4s.igt.R

class ProgressBarDialog : DialogFragment() {

    companion object {
        fun getInstance() = ProgressBarDialog()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog.window.setLayout(width, height)
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return activity?.layoutInflater?.inflate(R.layout.progress_dialog, container, false)
    }

}