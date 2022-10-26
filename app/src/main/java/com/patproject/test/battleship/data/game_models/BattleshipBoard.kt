package com.patproject.test.battleship.data.game_models

typealias OnFieldChangedListener = (battleshipBoard: BattleshipBoard) -> Unit

class BattleshipBoard(
    val rows: Int = 10,
    val columns: Int = 10
) {
    val listeners = mutableSetOf<OnFieldChangedListener>()

    private val cells = Array(rows) { Array(columns) { CellStates.FREE } }

    fun getCellByIndex(row: Int, column: Int): CellStates {
        when {
            row < 0 || column < 0 -> throw Exception("row index or column index is negative")
            row >= rows || column >= columns -> throw Exception("row index or column index is bigger than board maximum")
        }
        return cells[row][column]
    }

    fun setCellByIndex(row: Int, column: Int, cellStates: CellStates) {
        if (getCellByIndex(row, column) != cellStates) {
            listeners.forEach { it.invoke(this) }
            cells[row][column] = cellStates
        }

    }


}