package com.patproject.test.battleship.ui.custom_view_elements

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

class CustomBoardViewGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : ViewGroup(context,attrs,defStyleAttrs) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun measureChild(
        child: View?,
        parentWidthMeasureSpec: Int,
        parentHeightMeasureSpec: Int
    ) {
        super.measureChild(child, parentWidthMeasureSpec, parentHeightMeasureSpec)
    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        TODO("Not yet implemented")
    }
}