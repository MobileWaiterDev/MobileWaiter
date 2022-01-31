package com.mwaiterdev.waiter.ui.base.customview

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView

class SquareCardView(context : Context, attrs : AttributeSet) :MaterialCardView(context, attrs) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val heightMeasure: Int = widthMeasureSpec * 2
        super.onMeasure(widthMeasureSpec, heightMeasure)
    }
}