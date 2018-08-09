package com.example.mkrawcz.m8radar.common.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class SquareView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

//    constructor(context: Context) : this(context, null)
//    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
//    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val dimen = min(measuredHeight, measuredWidth)
        setMeasuredDimension(dimen, dimen)
    }
}