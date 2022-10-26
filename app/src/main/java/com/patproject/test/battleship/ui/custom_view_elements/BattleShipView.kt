package com.patproject.test.battleship.ui.custom_view_elements

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import com.patproject.test.battleship.R
import com.patproject.test.battleship.data.game_models.BattleshipBoard
import com.patproject.test.battleship.data.game_models.OnFieldChangedListener
import java.lang.Float.min
import java.lang.Integer.max
import kotlin.properties.Delegates

@SuppressLint("ViewConstructor")
class BattleShipView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttrs: Int,
    defStyleRes: Int,
) : View(context, attrs, defStyleAttrs, defStyleRes) {

    private val listener: OnFieldChangedListener = { _ ->
        invalidate()
    }

    private var board: BattleshipBoard? = null
        set(value) {
            board?.listeners?.remove(listener)
            field = value
            value?.listeners?.add(listener)
        }


    private var freeCellColor by Delegates.notNull<Int>()
    private var missedCellColor by Delegates.notNull<Int>()
    private var shotCellColor by Delegates.notNull<Int>()
    private var placedShipColor by Delegates.notNull<Int>()
    private var columnsAmount by Delegates.notNull<Int>()
    private var rowsAmount by Delegates.notNull<Int>()

    // safe zone rect where we can drawing
    private val battleShipBoardRect = RectF(0f, 0f, 0f, 0f)
    // size of one cell
    private var cellSize: Float = 0f
    // padding in the cell
    private var cellPadding: Float = 0f

    // helper variable to avoid object allocation in onDraw
    private val cellRect = RectF()

    // pre-initialized paints for drawing
    private var _freeCellPaint: Paint? = null
    private var _missedCellPaint: Paint? = null
    private var _shotCellPaint: Paint? = null
    private var _placedShipPaint: Paint? = null

    private val freeCellPaint: Paint
        get() = _freeCellPaint!!


    private val missedCellPaint: Paint
        get() = _missedCellPaint!!
    private val shotCellPaint: Paint
        get() = _shotCellPaint!!
    private val placedShipPaint: Paint
        get() = _placedShipPaint!!

    constructor(context: Context, attrs: AttributeSet?, defStyleAttrs: Int) : this(
        context,
        attrs,
        defStyleAttrs,
        R.style.DefaultBattleShipViewStyle
    )

    constructor(context: Context, attrs: AttributeSet?) : this(
        context,
        attrs,
        R.attr.battleShipViewStyle
    )

    constructor(context: Context) : this(context, null)

    init {
        if (attrs != null) {
            initAttrs(attrs, defStyleAttrs, defStyleRes)
        } else {
            initDefaultColors()
        }
    }

    private fun initDefaultColors() {
        freeCellColor =
            ContextCompat.getColor(context, com.patproject.test.ui.R.color.project_white_yellow)
        missedCellColor =
            ContextCompat.getColor(context, com.patproject.test.ui.R.color.project_gray)
        shotCellColor = ContextCompat.getColor(context, com.patproject.test.ui.R.color.project_red)
        placedShipColor =
            ContextCompat.getColor(context, com.patproject.test.ui.R.color.project_light_blue)
        columnsAmount = DEFAULT_COLUMN_AMOUNT
        rowsAmount = DEFAULT_ROW_AMOUNT
    }

    @SuppressLint("Recycle")
    private fun initAttrs(attrs: AttributeSet, defStyleAttrs: Int, defStyleRes: Int) {
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.BattleShipView,
            defStyleAttrs,
            defStyleRes
        )

        freeCellColor = typedArray.getColor(
            R.styleable.BattleShipView_freeCellColor,
            Color.parseColor(DEFAULT_FREE_CELL_COLOR)
        )
        missedCellColor = typedArray.getColor(
            R.styleable.BattleShipView_missedCellColor,
            Color.parseColor(DEFAULT_MISSED_CELL_COLOR)
        )
        shotCellColor = typedArray.getColor(
            R.styleable.BattleShipView_shotCellColor,
            Color.parseColor(DEFAULT_SHOT_CELL_COLOR)
        )
        placedShipColor = typedArray.getColor(
            R.styleable.BattleShipView_placedShipColor,
            Color.parseColor(DEFAULT_PLACED_SHIP_COLOR)
        )
        columnsAmount =
            typedArray.getInt(R.styleable.BattleShipView_columnsAmount, DEFAULT_COLUMN_AMOUNT)
        rowsAmount = typedArray.getInt(R.styleable.BattleShipView_rowsAmount, DEFAULT_ROW_AMOUNT)

        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minWidth = suggestedMinimumWidth
        val minHeight = suggestedMinimumHeight

        val rows = board?.rows ?: 0
        val columns = board?.columns ?: 0
        val desiredCellSizeInPixels = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, DESIRED_CELL_SIZE,
            resources.displayMetrics
        ).toInt()

        val desiredWidth = max(minWidth, desiredCellSizeInPixels*rows) + paddingLeft + paddingRight
        val desiredHeight = max(minHeight,desiredCellSizeInPixels*columns) + paddingTop + paddingBottom

        setMeasuredDimension(
            resolveSize(desiredWidth,widthMeasureSpec),
            resolveSize(desiredHeight,heightMeasureSpec)
        )
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val board = this.board ?: return

        val safeWidth = width - paddingLeft - paddingRight
        val safeHeight = height - paddingTop - paddingBottom

        val cellWidth = safeWidth / board.columns.toFloat()
        val cellHeight = safeHeight / board.rows.toFloat()

        cellSize = min(cellWidth, cellHeight)
        cellPadding = cellSize * 0.2f

        val fieldWidth = cellSize * board.columns
        val fieldHeight = cellSize * board.rows

        battleShipBoardRect.left = paddingLeft + (safeWidth - fieldWidth) / 2
        battleShipBoardRect.top = paddingTop + (safeHeight - fieldHeight) / 2
        battleShipBoardRect.right = battleShipBoardRect.left + fieldWidth
        battleShipBoardRect.bottom = battleShipBoardRect.top + fieldHeight
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }

    companion object {
        const val DEFAULT_COLUMN_AMOUNT = 10
        const val DEFAULT_ROW_AMOUNT = 10

        const val DESIRED_CELL_SIZE = 30f

        const val DEFAULT_FREE_CELL_COLOR = "#FEF5AC"
        const val DEFAULT_MISSED_CELL_COLOR = "#6C6C6C"
        const val DEFAULT_SHOT_CELL_COLOR = "#ED5454"
        const val DEFAULT_PLACED_SHIP_COLOR = "#97D2EC"
    }
}