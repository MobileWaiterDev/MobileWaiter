package com.mwaiterdev.waiter.ui.base

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView

class SquareCardView(context : Context, attrs : AttributeSet) : CardView(context, attrs) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val heightMeasure: Int = widthMeasureSpec + 40
        super.onMeasure(widthMeasureSpec, heightMeasure)
    }
}