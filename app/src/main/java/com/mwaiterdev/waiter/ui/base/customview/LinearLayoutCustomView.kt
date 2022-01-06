package com.mwaiterdev.waiter.ui.base.customview

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat

class LinearLayoutCustomView(context: Context, attrs: AttributeSet): LinearLayoutCompat(context, attrs) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val customHeight: Int = widthMeasureSpec*3
        super.onMeasure(widthMeasureSpec, customHeight)
    }
}