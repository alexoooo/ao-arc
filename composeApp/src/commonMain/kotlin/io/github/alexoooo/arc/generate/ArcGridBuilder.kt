package io.github.alexoooo.arc.generate

import io.github.alexoooo.arc.model.*


class ArcGridBuilder {
    //-----------------------------------------------------------------------------------------------------------------
    private val cells: MutableList<MutableList<ArcColor?>> =
        MutableList(ArcDimensions.max) {
            MutableList(ArcDimensions.max) {
                null
            }
        }


    //-----------------------------------------------------------------------------------------------------------------
    fun set(cell: ArcCell, color: ArcColor?) {
        set(cell.row, cell.column, color)
    }

    fun set(rowIndex: ArcCoordinate, columnIndex: ArcCoordinate, color: ArcColor?) {
        cells[rowIndex.index][columnIndex.index] = color
    }


    fun clear() {
        for (row in cells) {
            for (column in 0 ..< ArcDimensions.max) {
                row[column] = null
            }
        }
    }


    fun shiftRight(columns: Int) {
        for (i in 0 ..< columns) {
            for (row in cells) {
                row.add(0, null)
                row.removeLast()
            }
        }
    }


    fun shiftLeft(columns: Int) {
        for (i in 0 ..< columns) {
            for (row in cells) {
                row.removeFirst()
                row.add(null)
            }
        }
    }


    fun shiftDown(rows: Int) {
        for (i in 0 ..< rows) {
            cells.add(0, MutableList(ArcDimensions.max) { null })
            cells.removeLast()
        }
    }


    fun shiftUp(rows: Int) {
        for (i in 0 ..< rows) {
            cells.removeFirst()
            cells.add(0, MutableList(ArcDimensions.max) { null })
        }
    }


    //-----------------------------------------------------------------------------------------------------------------
    fun usedDimensions(): ArcDimensions {
        var emptyRowsFromBottom = 0
        for (row in ArcDimensions.max - 1 downTo 0) {
            if (cells[row].any { it != null }) {
                break
            }
            emptyRowsFromBottom++
        }

        var emptyColumnsFromRight = 0
        for (column in ArcDimensions.max - 1 downTo 0) {
            if (cells.any { it[column] != null }) {
                break
            }
            emptyColumnsFromRight++
        }

        val rows = ArcDimensions.max - emptyRowsFromBottom
        val columns = ArcDimensions.max - emptyColumnsFromRight
        return ArcDimensions(rows, columns)
    }


    fun build(
        background: ArcColor = ArcColor.Black,
        dimensions: ArcDimensions = usedDimensions()
    ): ArcGrid {
        val usedCells =
            (0 ..< dimensions.rows).map { row ->
                (0 ..< dimensions.columns).map { column ->
                    cells[row][column] ?: background
                }
            }

        return ArcGrid(dimensions, usedCells)
    }
}