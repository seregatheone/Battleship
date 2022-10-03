package com.patproject.test.battleship.ui.custom_view_elements

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import com.patproject.test.battleship.R
import java.lang.Integer.min

@SuppressLint("ViewConstructor")
class PlacementCellLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0,
    defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttrs, defStyleRes) {

    private val backgroundPaint = Paint()
    private val placedShipPaint = Paint()

    init{
        with(backgroundPaint){
            color = ContextCompat.getColor(context, com.patproject.test.ui.R.color.project_white_yellow)
            style = Paint.Style.FILL
            isAntiAlias = true
        }

        with(placedShipPaint){
            color = ContextCompat.getColor(context, com.patproject.test.ui.R.color.project_dark_blue)
            style = Paint.Style.FILL
            isAntiAlias = true
        }

    }
    override fun callOnClick(): Boolean {
        hasShip = !hasShip
        invalidate()
        return super.callOnClick()
    }

    var hasShip = false

    override fun onDraw(canvas: Canvas) {
        val boarderStrokeWidth = (min(width,height)/8).toFloat()
        val cornersRadius = (min(width,height)/10).toFloat()
        drawBackground(canvas,cornersRadius)
        if(!hasShip){
            drawShip(canvas,cornersRadius,boarderStrokeWidth)
        }
        super.onDraw(canvas)
    }
    private fun drawShip(canvas: Canvas,cornersRadius: Float,boarderStrokeWidth:Float){
        canvas.drawRoundRect(
            RectF(boarderStrokeWidth, boarderStrokeWidth, width-boarderStrokeWidth, height-boarderStrokeWidth),
            cornersRadius,
            cornersRadius,
            placedShipPaint
        )
    }
    private fun drawBackground(canvas: Canvas,cornersRadius: Float){
        canvas.drawRoundRect(
            RectF(0f, 0f, width.toFloat(), height.toFloat()),
            cornersRadius,
            cornersRadius,
            backgroundPaint
        )
    }
}